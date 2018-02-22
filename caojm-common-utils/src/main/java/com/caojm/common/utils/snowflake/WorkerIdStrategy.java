package com.caojm.common.utils.snowflake;

/**
 * workId生成策略
 *
 * @author <a href="mailto:910922164@qq.com">caojianmin</a>
 * @create 2018/02/17 22:41
 */
public interface WorkerIdStrategy {
    long getWorkerId();
}
