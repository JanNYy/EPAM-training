package ua.epam.pizzaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.domain.City;
import ua.epam.pizzaservice.repository.CityRepository;
import ua.epam.pizzaservice.service.CityService;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    @Override
    public Integer saveCity(City city) {
        return cityRepository.saveCity(city);
    }

    @Override
    public void deleteCity(Integer id) {
        cityRepository.deleteCity(id);
    }
}
