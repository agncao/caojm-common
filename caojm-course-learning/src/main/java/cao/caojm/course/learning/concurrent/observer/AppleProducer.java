package cao.caojm.course.learning.concurrent.observer;

import cao.caojm.course.learning.dto.Apple;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/27 16:28
 */
public class AppleProducer extends Producer<Apple>{

    // 并没有延迟加载
    public static AppleProducer INSTANCE = new AppleProducer(DEFAULT_QUEUE_LENGTH, 1);

    private AppleProducer(int capacity, int consumerCount) {
        super(capacity);
        System.out.println("begin init AppleConsumer ....");
        AppleConsumer consumer = new AppleConsumer(queue);
        System.out.println("begin consumerThread....");
        consumerThread(consumerCount, consumer);
    }

}

