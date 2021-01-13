package com.shuanger.snowworkiddemo.config;

import com.shuanger.snowworkiddemo.dao.WorkerIdManagerMapper;
import com.shuanger.snowworkiddemo.domain.WorkerIdManager;
import com.shuanger.snowworkiddemo.exception.BusinessException;
import com.shuanger.snowworkiddemo.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.zip.CRC32;


@Slf4j
@Configuration
public class IDGeneratorConfig {

    @Resource
    private WorkerIdManagerMapper workerIdManagerMapper;

    @Value("${spring.application.name:demo}")
    private String appName;

    private String ipAddress;

    private Long currentWorkerId;

    private static final long MAX_WORKER_ID = ~(-1L << 5);
    private static final long MAX_DATA_CENTER_ID = ~(-1L << 5);

    @Bean
    public SnowFlakeIDGenerator createGenerator() {
        this.ipAddress = IpUtil.getIp();
        currentWorkerId = this.applyWorkerId();
        CRC32 crc32 = new CRC32();
        crc32.update(this.ipAddress.getBytes());
        long dataCenterId = crc32.getValue() % MAX_DATA_CENTER_ID;
        return new SnowFlakeIDGenerator(currentWorkerId, dataCenterId);
    }

    private long applyWorkerId() {
        Assert.hasLength(ipAddress, "获取IP地址失败");
        List<WorkerIdManager> workerIds = workerIdManagerMapper.listAvailableWorkerIds(appName);
        for (WorkerIdManager idManager : workerIds) {
            idManager.setIpAddress(ipAddress);
            if (workerIdManagerMapper.occupyWorkerIdById(idManager) > 0) {
                return idManager.getWorkerId();
            }
        }
        Integer maxWorkerId = workerIdManagerMapper.selectMaxWorkerId(appName);
        if (null == maxWorkerId) {
            maxWorkerId = 0;
        } else {
            maxWorkerId = maxWorkerId + 1;
        }
        WorkerIdManager idManager = new WorkerIdManager(appName, ipAddress);
        for (int i = maxWorkerId; i <= MAX_WORKER_ID; i++) {
            idManager.setWorkerId(i);
            int res = workerIdManagerMapper.registerNewWorkerId(idManager);
            if (res > 0) {
                return i;
            }
        }
        throw new BusinessException("出异常了");
    }

    @PreDestroy
    private void doReleaseWorkerId() {
        log.info("release workerId appName:{} workerId:{} ip:{}", appName, currentWorkerId, ipAddress);
        if (null == currentWorkerId) {
            return;
        }
        WorkerIdManager idManager = new WorkerIdManager(appName, ipAddress);
        idManager.setWorkerId(currentWorkerId.intValue());
        workerIdManagerMapper.releaseWorkerId(idManager);
    }

}
