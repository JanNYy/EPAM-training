package ua.epam.pizzaservice.service.test;

import ua.epam.pizzaservice.domain.Customer;
import ua.epam.pizzaservice.domain.Order;
import ua.epam.pizzaservice.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.repository.OrderRepository;
import ua.epam.pizzaservice.repository.PizzaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 23.07.2015.
 */
//@Service(value = "orderService")
public class SimpleOrderService /*implements OrderService*/
//        , ApplicationContextAware
{

//    private ObjectFactory objectFactory = ObjectFactory.instance;

//    private ApplicationContext applicationContext;

    private PizzaRepository pizzaRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(PizzaRepository pizzaRepository,
                              OrderRepository orderRepository)
            throws Exception {
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setPizzaRepository(PizzaRepository pizzaRepository) {
//        System.out.println("PizzaRepo - testMeth");
        this.pizzaRepository = pizzaRepository;
    }

//    @Benchmark
    public Order placeNewOrder(Customer customer, Integer ... pizzasID) {

        List<Pizza> pizzas = new ArrayList<Pizza>();

        for (Integer id : pizzasID)
        {
            pizzas.add(pizzaRepository.getPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = getNewOrder();
        newOrder.setCustomer(customer);
//        newOrder.setPizzas(pizzas);

        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

//    @Lookup(value = "order")
    protected Order getNewOrder() {
//        return applicationContext.getBean("order",Order.class);
        return null;
    }

    @Override
    public String toString() {
        return "SimpleOrderService";
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//
//    }
}
