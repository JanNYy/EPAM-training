package ua.epam.pizzaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.domain.Address;
import ua.epam.pizzaservice.repository.AddressRepository;
import ua.epam.pizzaservice.service.AddressService;

/**
 * Created by Anna on 20.08.2015.
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address getAddressByID(Integer id) {
        return addressRepository.getAddressByID(id);
    }

    @Override
    public Integer saveAddress(Address address) {
        return addressRepository.saveAddress(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.deleteAddress(id);
    }
}
