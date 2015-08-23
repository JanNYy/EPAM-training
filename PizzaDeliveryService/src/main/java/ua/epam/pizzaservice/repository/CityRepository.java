package ua.epam.pizzaservice.repository;

import ua.epam.pizzaservice.domain.City;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
public interface CityRepository {

    List<City> getAllCities();

    City getCityByID(Integer id);

    City getCityByName(String name);

    Integer saveCity(City city);

    void deleteCity(Integer id);
}
