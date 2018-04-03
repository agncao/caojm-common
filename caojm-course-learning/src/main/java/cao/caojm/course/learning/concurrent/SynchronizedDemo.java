package cao.caojm.course.learning.concurrent;

/**
 * 运行后发现结果可能是： result的值为：0，原因是执行顺序有可能：1.2(线程1):此时ready=false -> 2.1(线程2) -> 2.3(线程2) -> 1.1(线程1)
 * 此例子主要是演示语句重排序在多线程中的影响
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/29 18:06
 */
public class SynchronizedDemo {
    //共享变量
    private boolean ready = false;
    private int result = 0;
    private int number = 1;
    //写操作
    public void write(){
        ready = true;                           //1.1
        number = 2;                            //1.2
    }
    //读操作
    public void read(){
        if(ready){                             //2.1
            result = number*3;                  //2.2
        }
        System.out.println("result的值为：" + result);  //2.3
    }

    //内部线程类
    private class ReadWriteThread extends Thread {
        //根据构造方法中传入的flag参数，确定线程执行读操作还是写操作
        private boolean flag;
        public ReadWriteThread(boolean flag){
            this.flag = flag;
        }
        @Override
        public void run() {
            if(flag){
                //构造方法中传入true，执行写操作
                write();
            }else{
                //构造方法中传入false，执行读操作
                read();
            }
        }
    }

    public static void main(String[] args)  {
        SynchronizedDemo synDemo = new SynchronizedDemo();
        //启动线程执行写操作
        synDemo .new ReadWriteThread(true).start(); //线程1
        //启动线程执行读操作
        synDemo.new ReadWriteThread(false).start(); //线程2
    }
}