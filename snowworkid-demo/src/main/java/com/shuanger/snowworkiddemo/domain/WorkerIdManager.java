package com.shuanger.snowworkiddemo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author automan
 * @desc
 * @date 2020/11/24
 */
@Getter
@Setter
@TableName("snow_worker_id_manager")
@NoArgsConstructor
public class WorkerIdManager {
    /**
     * 应用名称
     */
    private String appName;

    /**
     * worker id
     */
    private Integer workerId;

    /**
     * 引用次数
     */
    private Integer refCount;

    /**
     * IP 地址
     */
    private String ipAddress;

    public WorkerIdManager(String appName, String ipAddress) {
        this.appName = appName;
        this.ipAddress = ipAddress;
    }
}
