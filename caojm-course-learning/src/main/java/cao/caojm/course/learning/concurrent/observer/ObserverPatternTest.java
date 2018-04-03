package cao.caojm.course.learning.concurrent.observer;

import cao.caojm.course.learning.dto.Apple;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/27 16:36
 */
public class ObserverPatternTest {
    public static void main( String[] args ){
        AppleProducer producer = AppleProducer.INSTANCE;
        for (int i = 0; i < 60; i++) {
            producer.put(new Apple(i));
        }
    }
}
