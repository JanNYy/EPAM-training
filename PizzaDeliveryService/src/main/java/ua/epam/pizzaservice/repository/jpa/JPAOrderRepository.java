package ua.epam.pizzaservice.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.pizzaservice.domain.Order;
import ua.epam.pizzaservice.repository.AddressRepository;
import ua.epam.pizzaservice.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Anna on 21.08.2015.
 */
@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Order getOrderByID(Integer id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> getAllOrders() {
        TypedQuery<Order> query =
                em.createQuery("select o from Order o", Order.class);
        return query.getResultList();
    }

    @Override
    public List<Order> getAllOrdersByCustomerID(Integer customerID) {
        try
        {
            TypedQuery<Order> query =
                    em.createQuery("select o from Order o where o.customer.id = :customerID", Order.class);
            query.setParameter("customerID", customerID);
            return query.getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    @Transactional
    public Integer saveOrder(Order order) {
        if (order == null)
            return null;

        addressRepository.saveAddress(order.getAddress());

        if (order.getId() == null)
            em.persist(order);
        else
            em.merge(order);
        return order.getId();
    }

    @Override
    @Transactional
    public void deleteOrder(Integer id) {
        if (id == null)
            return;

        Order order = getOrderByID(id);

        if (order != null)
            em.remove(order);
    }
}
