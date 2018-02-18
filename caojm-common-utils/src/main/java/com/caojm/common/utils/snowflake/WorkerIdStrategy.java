package com.caojm.common.utils.snowflake;

/**
 * workId生成策略
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/17 22:41
 */
public interface WorkerIdStrategy {
    long getWorkerId();
}
