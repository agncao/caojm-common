package com.caojm.common.utils.snowflake;


import com.caojm.common.utils.snowflake.impl.CustomizedWorkerIdStrategy;
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
    private WorkerIdStrategy workerIdStrategy=new SimpleWorkerIdStrategy();
//    private WorkerIdStrategy workerIdStrategy=new CustomizedWorkerIdStrategy();

    @Test
    public void nextIdMultiThread(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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

    @Test
    public void nextId(){
        long startTime=System.currentTimeMillis();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        long workId = workerIdStrategy.getWorkerId();
        try {
            int k=0;
            while (k<100000) {
                long id = sequenceUtil.nextId(workId);
                System.out.println("id="+id);   //把这行注释掉，只需200毫秒
                k++;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println(String.format("Testing run over! consuming %d millisecond",System.currentTimeMillis()-startTime));
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
