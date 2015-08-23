package ua.epam.pizzaservice.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.pizzaservice.domain.Address;
import ua.epam.pizzaservice.domain.City;
import ua.epam.pizzaservice.domain.Street;
import ua.epam.pizzaservice.repository.AddressRepository;
import ua.epam.pizzaservice.repository.CityRepository;
import ua.epam.pizzaservice.repository.StreetRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Anna on 20.08.2015.
 */
@Repository("addressRepository")
public class JPAAddressRepository implements AddressRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StreetRepository streetRepository;

    @Override
    public Address getAddressByID(Integer id) {
        return em.find(Address.class, id);
    }

    @Override
    @Transactional
    public Integer saveAddress(Address address) {
        if (address == null)
            return null;

        City city = cityRepository.getCityByName(address.getCity().getName());
        if (city != null)
            address.setCity(city);
//        cityRepository.saveCity(address.getCity());

        Street street = streetRepository.getStreetByName(address.getStreet().getName());
        if (street != null)
            address.setStreet(street);
//        streetRepository.saveStreet(address.getStreet());

        if (address.getId() == null)
            em.persist(address);
        else
            em.merge(address);
        return address.getId();
    }

    @Override
    @Transactional
    public void deleteAddress(Integer id) {
        if (id == null)
            return;

        Address address = getAddressByID(id);

        if (address != null)
            em.remove(address);
    }
}
