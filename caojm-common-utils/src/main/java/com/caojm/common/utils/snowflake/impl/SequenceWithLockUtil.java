package com.caojm.common.utils.snowflake.impl;

import com.caojm.common.utils.snowflake.SequenceUtil;
import com.caojm.common.utils.snowflake.WorkerIdStrategy;
import org.springframework.stereotype.Component;

/**
 * 自增长id生成器
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/15 18:11
 */
@Component
public class SequenceWithLockUtil extends SequenceUtil {
    private long lastTimeMillis = -1L;//上一次时间戳
    private long sequence = 0L; //序列号

    @Override
    public synchronized long nextId(WorkerIdStrategy workerIdStrategy) {
        long workId = workerIdStrategy.getWorkerId();
        return this.getNextId(workId);
    }

    @Override
    public synchronized long nextId(long workId) {
        return this.getNextId(workId);
    }

    private long getNextMill() {
        long currentTime = System.currentTimeMillis();
        while (currentTime <= lastTimeMillis) {
            currentTime = System.currentTimeMillis();
        }
        return currentTime;
    }

    private long getNextId(long workId) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < lastTimeMillis) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currentTimeMillis == lastTimeMillis) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & Max_Sequence;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currentTimeMillis = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimeMillis = currentTimeMillis;

        long id= (currentTimeMillis) << Timestmp_Left //时间戳部分
                | workId << Machine_Left       //数据中心部分
                | sequence;                             //序列号部分
        return id;
    }

}
