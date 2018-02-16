package com.caojm.common.utils.sequence;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/16 13:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class SequenceNoLockUtilTest{
    @Resource
    SequenceNoLockUtil sequenceUtil;

    @Test
    public void nextId(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(sequenceUtil.datacenterId);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
