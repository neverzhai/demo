package com.shuanger.democommon.service;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 15:12
 * @description:
 */
public interface OperationRecordService {

    boolean saveRecord(String methodName, String value);
}
