package com.shuanger.snowworkiddemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @author yuchangcun
 * @desc
 * @date 2020/11/18
 */
@Slf4j
public class SnowFlakeIDGenerator {

    private static final long TWEPOCH = 1288834974657L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);


    private long lastTimestamp = -1L;
    private long sequence = 0L;
    private long workerId;
    private long dataCenterId;

    public SnowFlakeIDGenerator(long workerId, long dataCenterId) {
        Assert.isTrue(workerId >= 0 && workerId <= MAX_WORKER_ID, String.format("worker id can't be greater than %d or less than 0", MAX_WORKER_ID));
        Assert.isTrue(dataCenterId >= 0 && dataCenterId <= MAX_DATA_CENTER_ID, String.format("data center id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        log.info("id generator created: data center id: {}, worker id: {}", this.dataCenterId, this.workerId);
    }

    public synchronized long generate() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            log.warn("clock is moving backwards. wait {} milliseconds.", lastTimestamp - timestamp);
            timestamp = waitNextMills(lastTimestamp);
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = waitNextMills(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) | (dataCenterId << DATA_CENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    private long waitNextMills(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
