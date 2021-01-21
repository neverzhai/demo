package com.shuanger.democommon.service.impl;

import com.shuanger.democommon.service.OperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 15:12
 * @description:
 */
@Slf4j
@Service
public class OperationRecordServiceImpl implements OperationRecordService {

    public boolean saveRecord(String value) {
        log.info("记录操作日志:{}", value);

        return true;
    }
}
