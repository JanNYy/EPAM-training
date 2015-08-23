package ua.epam.pizzaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.domain.Customer;
import ua.epam.pizzaservice.repository.CustomerRepository;
import ua.epam.pizzaservice.service.CustomerService;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        return customerRepository.getCustomerByUserName(userName);
    }

    @Override
    public Customer getCustomerByID(Integer id) {
        return customerRepository.getCustomerByID(id);
    }

    @Override
    public Integer saveCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteCustomer(id);
    }
}
