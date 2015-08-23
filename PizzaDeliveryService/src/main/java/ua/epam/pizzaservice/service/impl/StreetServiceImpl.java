package ua.epam.pizzaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.domain.Street;
import ua.epam.pizzaservice.repository.StreetRepository;
import ua.epam.pizzaservice.service.StreetService;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Service("streetService")
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetRepository streetRepository;

    @Override
    public List<Street> getAllStreets() {
        return streetRepository.getAllStreets();
    }

    @Override
    public Integer saveStreet(Street street) {
        return streetRepository.saveStreet(street);
    }

    @Override
    public void deleteStreet(Integer id) {
        streetRepository.deleteStreet(id);
    }
}
