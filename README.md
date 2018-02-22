# caojm-common
工具包

## common/utils/snowflake 自增长id
* 使用方法
```  
    @Resource
    SequenceWithLockUtil sequenceUtil;
//    private WorkerIdStrategy workerIdStrategy=new SimpleWorkerIdStrategy();
    private WorkerIdStrategy workerIdStrategy=new CustomizedWorkerIdStrategy();
    
        long workId = workerIdStrategy.getWorkerId();
        long workId = workerIdStrategy.getWorkerId();
    
``` 