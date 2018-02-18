package com.caojm.common.utils.snowflake.impl;

import com.caojm.common.utils.snowflake.SequenceUtil;
import com.caojm.common.utils.snowflake.WorkerIdStrategy;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 自增长id生成器
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/16 11:46
 */
@Component
public class SequenceNoLockUtil extends SequenceUtil {

    /**
     * 计数器
     * 需要保证线程安全
     */
    private static volatile AtomicLong counter = new AtomicLong(0);
    private static volatile long currentTimeMillis = System.currentTimeMillis() - StartTimestamp;
    private static volatile long lastTimeMillis = currentTimeMillis;

    public long nextId(WorkerIdStrategy workerIdStrategy){
        long workId=workerIdStrategy.getWorkerId();
        return this.nextId(workId);
    }

    @Override
    public long nextId(long workId) {
        long sequence;
        while (true) {
            if (lastTimeMillis == currentTimeMillis) {
                sequence = counter.incrementAndGet();
                if (sequence >= (1 << 12) - 1) {//同一毫秒内可能达到series最大值
                    while (lastTimeMillis == currentTimeMillis) {//等待到下一毫秒
                        currentTimeMillis = System.currentTimeMillis() - StartTimestamp;
                    }
                    counter.compareAndSet(sequence, 0);
                    continue;
                }
                break;
            }
            lastTimeMillis = currentTimeMillis;
        }

        long id= (currentTimeMillis) << Timestmp_Left //时间戳部分
                | workId << Machine_Left       //数据中心部分
                | sequence;                             //序列号部分

        return id;
    }

}
