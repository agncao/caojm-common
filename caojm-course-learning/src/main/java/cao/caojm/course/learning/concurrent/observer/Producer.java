package cao.caojm.course.learning.concurrent.observer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/27 16:26
 */
public abstract class Producer <E> {
    protected BlockingQueue<E> queue;
    protected ExecutorService threadPool = Executors.newCachedThreadPool();
    public static final int DEFAULT_QUEUE_LENGTH = 10000;

    public Producer(int capacity) {
        initQueue(capacity);
    }

    public BlockingQueue<E> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<E> queue) {
        this.queue = queue;
    }

    public boolean put(E apple) {
        return queue.offer(apple);
    }

    private void initQueue(int capacity) {
        if (queue == null) {
            synchronized (this) {
                if (queue == null) {
                    queue = new LinkedBlockingDeque<E>(capacity < 0 ? DEFAULT_QUEUE_LENGTH : capacity);
                }
            }
        }
        System.out.print("the blockQueue has initialized：");
        System.out.println(queue);
    }

    protected void consumerThread(int consumerCount, Consumer<E> consumer) {
        for (int i = 0; i < consumerCount; i++) {
            threadPool.execute(consumer);
        }
    }
}
