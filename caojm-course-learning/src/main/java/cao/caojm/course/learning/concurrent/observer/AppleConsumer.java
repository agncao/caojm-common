package cao.caojm.course.learning.concurrent.observer;


import cao.caojm.course.learning.dto.Apple;

import java.util.concurrent.BlockingQueue;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/27 16:29
 */
public class AppleConsumer extends Consumer<Apple>{

    public AppleConsumer(BlockingQueue<Apple> queue) {
        super(queue);
    }

    @Override
    protected void consume(Apple data) {
        System.out.println(Thread.currentThread().getName()+" consume "+data);
    }
}

