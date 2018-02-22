# caojm-common
工具包

## common/utils/snowflake 自增长id
* 使用方法
    *参见测试用例SequenceWithLockUtilTest或者SequenceNoLockUtilTest*
```  
    @Resource
    SequenceWithLockUtil sequenceUtil;
//    private WorkerIdStrategy workerIdStrategy=new SimpleWorkerIdStrategy();
    private WorkerIdStrategy workerIdStrategy=new CustomizedWorkerIdStrategy();
    
        long workId = workerIdStrategy.getWorkerId();
                long id = sequenceUtil.nextId(workId);
                System.out.println("id="+id);
    
``` 

### CustomizedWorkerIdStrategy 配置
 * 需要在 spring-config-snowflake.xml 中指定当前应用所部署容器ip的workid,指定的workid不重复即可
 
### SimpleWorkerIdStrategy 
 * 把当前应用所部署容器ip的二级制后10位作为workid