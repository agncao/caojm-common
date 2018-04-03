package cao.caojm.course.learning.annotbean;

import cao.caojm.course.learning.dto.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试注解@Bean 的方式定义bean
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/04/02 11:59
 */
public class CommonConfigueTest {
    public static void main(String[] args) {
        //测试注解bean什么时候实例化的
        ApplicationContext context = new AnnotationConfigApplicationContext(CommonConfigue.class);
        System.out.println(context.getBean(Order.class));
    }
}

/*
* 第一：如果你使用BeanFactory作为Spring Bean的工厂类，则所有的bean都是在第一次使用该Bean的时候实例化
* 第二：如果你使用ApplicationContext作为Spring Bean的工厂类，则又分为以下几种情况：
*   （1）：如果bean的scope是singleton的，并且lazy-init为false（默认是false，所以可以不用设置），则ApplicationContext启动的时候就实例化该Bean，并且将实例化的Bean放在一个map结构的缓存中，下次再使用该Bean的时候，直接从这个缓存中取
*   （2）：如果bean的scope是singleton的，并且lazy-init为true，则该Bean的实例化是在第一次使用该Bean的时候进行实例化
*   （3）：如果bean的scope是prototype的，则该Bean的实例化是在第一次使用该Bean的时候进行实例化
* */