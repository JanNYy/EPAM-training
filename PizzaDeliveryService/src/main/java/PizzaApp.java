import ua.epam.pizzaservice.domain.Customer;
import ua.epam.pizzaservice.domain.Order;
import ua.epam.pizzaservice.service.OrderService;
import ua.epam.pizzaservice.infrastructure.*;

/**
 * Created by Anna on 23.07.2015.
 */
public class PizzaApp {

    public static void main(String[] args) throws Throwable {

//        Customer customer = new Customer(1, "Customer_1");
        ObjectFactory objectFactory = ObjectFactory.instance;

//        OrderService orderService = (OrderService) objectFactory.createObject("orderService");

        Config config = new JavaConfig();
        ApplicationContext context = new JavaConfigApplicationContext(config);
//        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
//        System.out.println(pizzaRepository);

        OrderService orderService = (OrderService) context.getBean("orderService");
        System.out.println(orderService.getClass());

//        Order order = orderService.placeNewOrder(customer, 1, 2, 3);

//        System.out.println(order);
    }

}
