package ua.epam.pizzaservice.repository;

import ua.epam.pizzaservice.domain.Order;

import java.util.List;

/**
 * Created by Anna on 23.07.2015.
 */
public interface OrderRepository {

    Order getOrderByID(Integer id);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByCustomerID(Integer customerID);

    Integer saveOrder(Order order);

    void deleteOrder(Integer id);
}
