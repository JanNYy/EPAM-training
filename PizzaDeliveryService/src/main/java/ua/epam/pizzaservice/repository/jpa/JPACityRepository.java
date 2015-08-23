package ua.epam.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.pizzaservice.domain.City;
import ua.epam.pizzaservice.repository.CityRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Repository("cityRepository")
public class JPACityRepository implements CityRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public List<City> getAllCities() {
        TypedQuery<City> query =
                em.createQuery("select c from City c", City.class);
        return query.getResultList();
    }

    @Override
    public City getCityByID(Integer id) {
        return em.find(City.class, id);
    }

    @Override
    @Transactional
    public City getCityByName(String name) {
        try
        {
            TypedQuery<City> query =
                    em.createQuery("select c from City c where c.name = :name", City.class);
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
    public Integer saveCity(City city) {
        if (city == null)
            return null;

        if (city.getId() == null)
            em.persist(city);
        else
            em.merge(city);
        return city.getId();
    }

    @Override
    @Transactional
    public void deleteCity(Integer id) {
        if (id == null)
            return;

        City city = getCityByID(id);

        if (city != null)
            em.remove(city);
    }
}
