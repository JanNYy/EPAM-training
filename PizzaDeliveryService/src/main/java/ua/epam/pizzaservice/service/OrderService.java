package ua.epam.pizzaservice.service;

import ua.epam.pizzaservice.domain.Customer;
import ua.epam.pizzaservice.domain.Order;

import java.util.List;

/**
 * Created by Anna on 23.07.2015.
 */
public interface OrderService {

//    Order placeNewOrder(Customer customer, Integer ... pizzasID);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByCustomerID(Integer customerID);

    Integer saveOrder(Order order);

    void deleteOrder(Integer id);
}
