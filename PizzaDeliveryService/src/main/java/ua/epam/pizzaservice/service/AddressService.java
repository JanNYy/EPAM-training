package ua.epam.pizzaservice.service;

import ua.epam.pizzaservice.domain.Address;

/**
 * Created by Anna on 20.08.2015.
 */
public interface AddressService {

    Address getAddressByID(Integer id);

    Integer saveAddress(Address address);

    void deleteAddress(Integer id);
}
