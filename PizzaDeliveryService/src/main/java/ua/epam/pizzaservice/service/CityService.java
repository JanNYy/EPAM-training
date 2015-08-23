package ua.epam.pizzaservice.service;

import ua.epam.pizzaservice.domain.City;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
public interface CityService {

    List<City> getAllCities();

    Integer saveCity(City city);

    void deleteCity(Integer id);
}
