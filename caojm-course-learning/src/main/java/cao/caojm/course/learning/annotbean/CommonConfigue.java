package cao.caojm.course.learning.annotbean;

import cao.caojm.course.learning.dto.Order;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/04/02 11:56
 */
@Configurable
public class CommonConfigue {
    @Bean
    public Order getOrder(){
        System.out.println("begin to create a new Order data");
        return new Order();
    }
}
