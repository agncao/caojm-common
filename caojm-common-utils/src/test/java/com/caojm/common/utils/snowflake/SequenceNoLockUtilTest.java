package com.caojm.common.utils.snowflake;


import com.caojm.common.utils.snowflake.impl.SequenceNoLockUtil;
import com.caojm.common.utils.snowflake.impl.SimpleWorkerIdStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/16 13:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class SequenceNoLockUtilTest{
    @Resource
    SequenceNoLockUtil sequenceUtil;
    static Set<Long> set = new ConcurrentSkipListSet<>();

    @Test
    public void nextId(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        WorkerIdStrategy workerIdStrategy=new SimpleWorkerIdStrategy();
        long workId = workerIdStrategy.getWorkerId();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+" start....");
                try {
                    while (true) {
                        System.out.println("sequenceUtil.nextId() beginning...");
                        long id = sequenceUtil.nextId(workId);
                        System.out.println(Thread.currentThread().getName()+", id="+id);
                        boolean add = set.add(id);
                        if (!add) {
                            System.out.println(System.currentTimeMillis() + "  " + id + " : " + Long.toBinaryString(id));
                            System.exit(0);
                        }
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            });
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
