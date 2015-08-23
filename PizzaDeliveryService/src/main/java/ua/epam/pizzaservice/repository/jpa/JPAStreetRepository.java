package ua.epam.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.pizzaservice.domain.Street;
import ua.epam.pizzaservice.repository.StreetRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Repository("streetRepository")
public class JPAStreetRepository implements StreetRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public List<Street> getAllStreets() {
        TypedQuery<Street> query =
                em.createQuery("select s from Street s", Street.class);
        return query.getResultList();
    }

    @Override
    public Street getStreetByID(Integer id) {
        return em.find(Street.class, id);
    }

    @Override
    public Street getStreetByName(String name) {
        try
        {
            TypedQuery<Street> query =
                    em.createQuery("select s from Street s where s.name = :name", Street.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    @Transactional
    public Integer saveStreet(Street street) {
        if (street == null)
            return null;

        if (street.getId() == null)
            em.persist(street);
        else
            em.merge(street);
        return street.getId();
    }

    @Override
    @Transactional
    public void deleteStreet(Integer id) {
        if (id == null)
            return;

        Street street = getStreetByID(id);

        if (street != null)
            em.remove(street);
    }
}
