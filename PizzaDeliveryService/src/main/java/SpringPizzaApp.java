import ua.epam.pizzaservice.domain.Customer;
import ua.epam.pizzaservice.domain.Order;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.pizzaservice.repository.PizzaRepository;
import ua.epam.pizzaservice.service.OrderService;

/**
 * Created by Anna on 24.07.2015.
 */
public class SpringPizzaApp {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext("repositoryContext.xml");

        ConfigurableApplicationContext applicationContext
                = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"},repositoryContext);

        PizzaRepository pizzaRepository = (PizzaRepository) applicationContext.getBean("pizzaRepository");
//        System.out.println(pizzaRepository);

        String[] beans = applicationContext.getBeanDefinitionNames();
        System.out.println("Context - start");
        for (String b : beans)
        {
            System.out.println(b);
        }
        System.out.println("Context - finish");

//        Customer customer = new Customer(1, "Customer_1");
        OrderService orderService = (OrderService) applicationContext.getBean(OrderService.class);

//        Order order = orderService.placeNewOrder(customer, 1,2,3);

//        System.out.println(order);

        System.out.println(applicationContext.getBean("order",Order.class));
        System.out.println(applicationContext.getBean("order",Order.class));

        repositoryContext.close();

        applicationContext.close();

    }
}
