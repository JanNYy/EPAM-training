package ua.epam.pizzaservice.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.pizzaservice.domain.Customer;
import ua.epam.pizzaservice.repository.AddressRepository;
import ua.epam.pizzaservice.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Repository("customerRepository")
public class JPACustomerRepository implements CustomerRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

//    @Autowired
//    private AddressRepository addressRepository;

    @Override
    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query =
                em.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        TypedQuery<Customer> query =
                em.createQuery("select c from Customer c where c.user.name = :name", Customer.class);
        query.setParameter("name", userName);
        return query.getSingleResult();
    }

    @Override
    public Customer getCustomerByID(Integer id) {
        return em.find(Customer.class, id);
    }

    @Override
    @Transactional
    public Integer saveCustomer(Customer customer) {
        if (customer == null)
            return null;

//        addressRepository.saveAddress(customer.getAddress());

        if (customer.getId() == null)
            em.persist(customer);
        else
            em.merge(customer);
        return customer.getId();
    }

    @Override
    @Transactional
    public void deleteCustomer(Integer id) {
        if (id == null)
            return;

        Customer customer = getCustomerByID(id);

        if (customer != null)
            em.remove(customer);
    }
}
