package com.caojm.common.utils.snowflake.impl;

import com.caojm.common.utils.SystemIpUtil;
import com.caojm.common.utils.snowflake.IPConvertor;
import com.caojm.common.utils.snowflake.WorkerIdStrategy;

import java.util.Set;

/**
 * 简单策略，以ip的二进制表示法的后十位作为workid
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/17 22:51
 */
public class SimpleWorkerIdStrategy implements WorkerIdStrategy {
    @Override
    public long getWorkerId() {
        Set<String> ips = SystemIpUtil.getIp();
        if(ips.isEmpty()){
            throw new IllegalArgumentException("SystemIpUtil.getIp returns empty set");
        }
        String ip=ips.toArray(new String[]{})[0];

        long workId = -1;
        //1、得到二进制字符串
        String binaryIp = IPConvertor.toBinary(IPConvertor.toLong(ip));
        binaryIp = binaryIp.substring(22);
        System.out.println("后十位：" + binaryIp);
        workId = Long.valueOf(binaryIp, 2);
        return workId;
    }
}
