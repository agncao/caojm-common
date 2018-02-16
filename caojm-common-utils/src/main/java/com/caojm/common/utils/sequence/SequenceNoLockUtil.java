package com.caojm.common.utils.sequence;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public long nextId() {
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
//        return (currentTimeMillis << 22) | (pid << 12) | sequence;

        return (currentTimeMillis) << Timestmp_Left //时间戳部分
                | getWorkIdByIp() << Datacenter_Left       //数据中心部分
                | sequence;                             //序列号部分
    }

}
