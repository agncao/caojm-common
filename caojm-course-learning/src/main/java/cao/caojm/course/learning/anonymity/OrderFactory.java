package cao.caojm.course.learning.anonymity;

import cao.caojm.course.learning.dto.Order;

/**
 * 订单创建工厂
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/04/03 14:56
 */
public interface OrderFactory {
    Order createInstance(Long orderId);
}
