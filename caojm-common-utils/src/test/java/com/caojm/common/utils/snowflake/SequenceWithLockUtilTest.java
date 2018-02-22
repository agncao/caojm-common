package com.caojm.common.utils.snowflake;


import com.caojm.common.utils.snowflake.impl.CustomizedWorkerIdStrategy;
import com.caojm.common.utils.snowflake.impl.SequenceWithLockUtil;
import com.caojm.common.utils.snowflake.impl.SimpleWorkerIdStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:910922164@qq.com">caojianmin</a>
 * @create 2018/02/16 13:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class SequenceWithLockUtilTest {
    @Resource
    SequenceWithLockUtil sequenceUtil;
    private static final int threadCount = 4;
//    private WorkerIdStrategy workerIdStrategy=new SimpleWorkerIdStrategy();
    private WorkerIdStrategy workerIdStrategy=new CustomizedWorkerIdStrategy();

    @Test
    public void nextId(){
        long startTime=System.currentTimeMillis();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        long workId = workerIdStrategy.getWorkerId();
        try {
            int k=0;
            while (k<100000) {
                long id = sequenceUtil.nextId(workId);
//                System.out.println("id="+id);
                k++;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println(String.format("Testing run over! consuming %d millisecond",System.currentTimeMillis()-startTime));
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }


    @Test
    public void nextIdMultiThread(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        long startTime=System.currentTimeMillis();
        long workId = workerIdStrategy.getWorkerId();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName()+" start....");
                try {
                    int k=0;
                    while (k<100000) {
                        System.out.println("sequenceUtil.nextId() beginning...");
                        long id = sequenceUtil.nextId(workId);
                        System.out.println(Thread.currentThread().getName()+", id="+id);
                        k++;
                    }
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await(20L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        executorService.shutdown();

        System.out.println(String.format("Testing run over! consuming %d millisecond",System.currentTimeMillis()-startTime));
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

    }
}
