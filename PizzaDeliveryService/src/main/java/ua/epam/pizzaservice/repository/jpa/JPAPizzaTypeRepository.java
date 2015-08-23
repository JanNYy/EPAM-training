package ua.epam.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import ua.epam.pizzaservice.domain.PizzaTypeSeparate;
import ua.epam.pizzaservice.domain.PizzaType;
import ua.epam.pizzaservice.repository.PizzaTypeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anna on 21.08.2015.
 */
@Deprecated
@Repository("pizzaTypeRepository")
public class JPAPizzaTypeRepository implements PizzaTypeRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public PizzaTypeSeparate getPizzaTypeByEnum(PizzaType typeEnum) {
        TypedQuery<PizzaTypeSeparate> query =
                em.createQuery("select pt from PizzaTypeSeparate pt where pt.name = :typeEnum", PizzaTypeSeparate.class);
        query.setParameter("typeEnum", typeEnum);
        return query.getSingleResult();
    }

    @Override
    public List<PizzaType> getAllPizzaTypes() {
        return Arrays.asList(PizzaType.values());
    }
}
