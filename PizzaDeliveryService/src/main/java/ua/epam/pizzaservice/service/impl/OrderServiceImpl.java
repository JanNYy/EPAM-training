package ua.epam.pizzaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.domain.Order;
import ua.epam.pizzaservice.repository.OrderRepository;
import ua.epam.pizzaservice.service.OrderService;

import java.util.List;

/**
 * Created by Anna on 21.08.2015.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public List<Order> getAllOrdersByCustomerID(Integer customerID) {
        return orderRepository.getAllOrdersByCustomerID(customerID);
    }

    @Override
    public Integer saveOrder(Order order) {
        return orderRepository.saveOrder(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteOrder(id);
    }
}
