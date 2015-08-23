package ua.epam.pizzaservice.repository;

import ua.epam.pizzaservice.domain.Address;

/**
 * Created by Anna on 20.08.2015.
 */
public interface AddressRepository {

    Address getAddressByID(Integer id);

    Integer saveAddress(Address address);

    void deleteAddress(Integer id);
}
