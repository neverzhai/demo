package com.shuanger.snowworkiddemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuanger.snowworkiddemo.domain.WorkerIdManager;

import java.util.List;

/**
 * @author automan
 * @desc
 * @date 2020/11/24
 */
public interface WorkerIdManagerMapper extends BaseMapper<WorkerIdManager> {

    List<WorkerIdManager> listAvailableWorkerIds(String appName);

    Integer selectMaxWorkerId(String appName);

    int occupyWorkerIdById(WorkerIdManager data);

    int registerNewWorkerId(WorkerIdManager data);

    int releaseWorkerId(WorkerIdManager data);
}
