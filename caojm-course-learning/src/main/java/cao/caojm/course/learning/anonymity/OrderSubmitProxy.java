package cao.caojm.course.learning.anonymity;

import cao.caojm.course.learning.dto.Order;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/04/03 15:57
 */
public class OrderSubmitProxy {
    private OrderFactory orderFactory;

    public OrderSubmitProxy(OrderFactory orderFactory){
        this.orderFactory=orderFactory;
    }
    public OrderSubmitProxy(){
    }

    public Order submit(Long orderId){
        Order order = orderFactory.createInstance(orderId);
        System.out.println("1、submit the order: "+order);
        return order;
    }

    public Order submit(Long orderId,OrderFactory orderFactory){
        if(this.orderFactory==null)
            this.orderFactory=orderFactory;
        Order order= orderFactory.createInstance(orderId);
        System.out.println("2、submit the order: "+order);
        return order;
    }

}
