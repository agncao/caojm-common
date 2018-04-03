package cao.caojm.course.learning.concurrent;

/**
 * 可见性变量未必能保持原子性测试：多运行几次会发现每次打印值都有可能不一样
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/30 13:57
 */
public class ValatileVarAtomicityTest {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final ValatileVarAtomicityTest test = new ValatileVarAtomicityTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<10000;j++) {
                        test.increase();
//                        System.out.println(Thread.currentThread().getName()+", inc="+test.inc);
                    }
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
