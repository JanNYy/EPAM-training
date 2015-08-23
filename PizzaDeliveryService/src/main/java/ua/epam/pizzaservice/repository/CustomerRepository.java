package ua.epam.pizzaservice.repository;

import ua.epam.pizzaservice.domain.Customer;

import java.util.List;

/**
 * Created by Anna on 04.08.2015.
 */
public interface CustomerRepository {

    List<Customer> getAllCustomers();

    Customer getCustomerByUserName(String userName);

    Customer getCustomerByID(Integer id);

    Integer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
