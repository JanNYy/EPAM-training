package ua.epam.pizzaservice.service;

import ua.epam.pizzaservice.domain.Customer;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerByUserName(String userName);

    Customer getCustomerByID(Integer id);

    Integer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
