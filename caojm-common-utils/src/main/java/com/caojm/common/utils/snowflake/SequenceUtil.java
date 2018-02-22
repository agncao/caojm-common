package com.caojm.common.utils.snowflake;

/**
 * 自增长生成器
 *
 * @author <a href="mailto:910922164@qq.com">caojianmin</a>
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

    abstract public long nextId(WorkerIdStrategy workerIdStrategy);
    abstract public long nextId(long workId);
}
