package cao.caojm.course.learning.anonymity;

import cao.caojm.course.learning.dto.Order;

/**
 * 匿名类测试
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/04/03 15:22
 */
public class AnonymousTest {
    public static void main(String[] args) {
        new OrderSubmitProxy(orderId -> {
            Order o = new Order();
            o.setId(orderId.toString());
            return o;
        }).submit(33218992873L);

        new OrderSubmitProxy().submit(271616832L,(orderId) -> {
            Order o = new Order();
            o.setId(orderId.toString());
            return o;
        });
    }
}
