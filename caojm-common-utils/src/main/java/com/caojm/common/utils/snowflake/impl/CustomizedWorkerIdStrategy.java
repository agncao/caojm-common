package com.caojm.common.utils.snowflake.impl;

import com.caojm.common.utils.SpringContextHolder;
import com.caojm.common.utils.SystemIpUtil;
import com.caojm.common.utils.snowflake.WorkerIdStrategy;
import org.springframework.beans.factory.annotation.Value;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * workId配置式策略
 *
 * @author <a href="mailto:910922164@qq.com">caojianmin</a>
 * @create 2018/02/17 22:55
 */
public class CustomizedWorkerIdStrategy implements WorkerIdStrategy{

    /**
     * 进程。
     * 这里也可以手动指定每台实例的ID号；或者通过ZK的临时递增节点自动获取。
     * 固定值
     */
    protected Long workId;

    @Override
    public long getWorkerId() {
        Long workId=null;
        Map<String,String> sequenceSnowflakeWorkIdMap = SpringContextHolder.getBean("sequenceSnowflakeWorkIdMap",Map.class);
        Set<String> localIps = SystemIpUtil.getIp();
        if(localIps.isEmpty())
            throw new IllegalArgumentException("SystemIpUtil.getIp returns empty value");
        Iterator<String> localIpsIter = localIps.iterator();
        while (localIpsIter.hasNext()){
            String ip = localIpsIter.next();
            if(sequenceSnowflakeWorkIdMap.get(ip)!=null){
                workId = Long.parseLong(sequenceSnowflakeWorkIdMap.get(ip));
                break;
            }
        }
        if(workId==null)
            throw new IllegalArgumentException("getWorkId returns null value");
        return workId;
    }
}
