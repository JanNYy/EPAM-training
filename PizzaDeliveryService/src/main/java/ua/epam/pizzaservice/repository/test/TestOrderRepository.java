package ua.epam.pizzaservice.repository.test;

import ua.epam.pizzaservice.domain.Order;
import ua.epam.pizzaservice.infrastructure.Benchmark;
import org.springframework.stereotype.Repository;
import ua.epam.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 23.07.2015.
 */
//@Repository
public class TestOrderRepository /*implements OrderRepository*/ {

    private List<Order> orderList = new ArrayList<Order>();

    @Benchmark
    public void saveOrder(Order order) {
//        order.setId(orderList.size()+1);
        orderList.add(order);
    }

    @Override
    public String toString() {
        return "TestOrderRepository";
    }
}
