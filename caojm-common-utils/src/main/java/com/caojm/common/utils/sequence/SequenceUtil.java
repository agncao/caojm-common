package com.caojm.common.utils.sequence;

import com.caojm.common.utils.SystemIpUtil;

import java.util.Set;

/**
 * 自增长生成器
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/16 11:46
 */
public abstract class SequenceUtil {
    /**
     * 起始的时间戳
     */
    protected final static long StartTimestamp = 1514476800000L;  //2017-12-29

    /**
     * 每一部分占用的位数
     */
    protected final static long Sequence_Bit = 12; //序列号占用的位数
    protected final static long Machine_Bit = 5;   //机器标识占用的位数
    protected final static long Datacenter_Bit = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    protected final static long Max_Datacenter_Num = -1L ^ (-1L << Datacenter_Bit);
    protected final static long Max_Machine_Num = -1L ^ (-1L << Machine_Bit);
    protected final static long Max_Sequence = -1L ^ (-1L << Sequence_Bit);

    /**
     * 每一部分向左的位移
     */
    protected final static long Machine_Left = Sequence_Bit;
    protected final static long Datacenter_Left = Sequence_Bit + Machine_Bit;
    protected final static long Timestmp_Left = Datacenter_Left + Datacenter_Bit;

    /**
     * 进程。
     * 这里也可以手动指定每台实例的ID号；或者通过ZK的临时递增节点自动获取。
     * 固定值
     */
    protected static final int pid = 3;   //TODO:在properties中配置

    /**
     * 将二进制ip地址的后十位作为workId
     *
     * @return
     */
    protected static long getWorkIdByIp(){
        Set<String> ips =SystemIpUtil.getIp();
        String ip = ips.toArray(new String[]{})[0];

        long workId=-1;
        //1、得到二进制字符串
        String binaryIp=IPConvertor.toBinary(IPConvertor.toLong(ip));
        binaryIp=binaryIp.substring(22);
        System.out.println("后十位："+binaryIp);
        workId = Long.valueOf(binaryIp,2);
        return workId;
    }

    abstract public long nextId();
}
