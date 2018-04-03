package cao.caojm.course.learning.concurrent.observer;

import java.util.concurrent.BlockingQueue;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/27 16:26
 */
public abstract class Consumer<E> implements Runnable{
    BlockingQueue<E> queue;

    /**
     * 数据逐个处理
     * @param data
     */
    protected abstract void consume(E data);

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName()+" begin take apple...");
                E data = take();
                try {
                    consume(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Consumer(BlockingQueue<E> queue) {
        this.queue = queue;
    }

    public E take() throws InterruptedException {
        return queue.take();
    }
}