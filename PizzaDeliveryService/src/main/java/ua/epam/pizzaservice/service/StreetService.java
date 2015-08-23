package ua.epam.pizzaservice.service;

import ua.epam.pizzaservice.domain.Street;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
public interface StreetService {

    List<Street> getAllStreets();

    Integer saveStreet(Street street);

    void deleteStreet(Integer id);
}
