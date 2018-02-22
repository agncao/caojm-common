package com.caojm.common.utils.snowflake.impl;

import com.caojm.common.utils.snowflake.SequenceUtil;
import com.caojm.common.utils.snowflake.WorkerIdStrategy;
import org.springframework.stereotype.Component;

/**
 * 利用数据库的自增长机制来得到sequence
 *
 * @author <a href="mailto:910922164@qq.com">caojianmin</a>
 * @create 2018/02/22 17:23
 */
@Component
public class JdbcWorkIdStrategy extends SequenceUtil {
    @Override
    public long nextId(WorkerIdStrategy workerIdStrategy) {
        return 0;
    }

    @Override
    public long nextId(long workId) {
        return 0;
    }
}
