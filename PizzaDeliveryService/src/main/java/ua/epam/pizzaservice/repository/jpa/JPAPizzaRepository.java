package ua.epam.pizzaservice.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.pizzaservice.domain.Pizza;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.pizzaservice.domain.PizzaType;
import ua.epam.pizzaservice.repository.PizzaRepository;
import ua.epam.pizzaservice.repository.PizzaTypeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Anna on 06.08.2015.
 */
@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

//    @Autowired
//    private PizzaTypeRepository pizzaTypeRepository;

    @Override
    public List<Pizza> getAllPizzas() {
        TypedQuery<Pizza> query =
                em.createQuery("select p from Pizza p", Pizza.class);
        return query.getResultList();
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        return em.find(Pizza.class, id);
    }

    @Override
    @Transactional
    public Integer savePizza(Pizza pizza) {
        if (pizza == null)
            return null;

        if (pizza.getId() == null)
            em.persist(pizza);
        else
            em.merge(pizza);
        return pizza.getId();
    }

    @Override
    @Transactional
    public void deletePizza(Integer id) {
        if (id == null)
            return;

        Pizza pizza = getPizzaByID(id);

        if (pizza != null)
            em.remove(pizza);
    }

    @Override
    public PizzaType[] getAllPizzaTypes() {
        return PizzaType.values();
    }
}
