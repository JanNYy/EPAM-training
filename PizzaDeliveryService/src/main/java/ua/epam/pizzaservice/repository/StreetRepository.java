package ua.epam.pizzaservice.repository;

import ua.epam.pizzaservice.domain.Street;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
public interface StreetRepository {
    
    List<Street> getAllStreets();

    Street getStreetByID(Integer id);

    Street getStreetByName(String name);

    Integer saveStreet(Street street);

    void deleteStreet(Integer id);
}
