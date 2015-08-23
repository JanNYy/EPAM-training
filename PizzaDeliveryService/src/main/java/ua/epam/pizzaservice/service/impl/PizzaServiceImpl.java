package ua.epam.pizzaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.domain.Pizza;
import ua.epam.pizzaservice.domain.PizzaTypeSeparate;
import ua.epam.pizzaservice.domain.PizzaType;
import ua.epam.pizzaservice.repository.PizzaRepository;
import ua.epam.pizzaservice.repository.PizzaTypeRepository;
import ua.epam.pizzaservice.service.PizzaService;

import java.util.List;

/**
 * Created by Anna on 21.08.2015.
 */
@Service("pizzaService")
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

//    @Autowired
//    private PizzaTypeRepository pizzaTypeRepository;

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.getAllPizzas();
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        return pizzaRepository.getPizzaByID(id);
    }

    @Override
    public Integer savePizza(Pizza pizza) {
        return pizzaRepository.savePizza(pizza);
    }

    @Override
    public void deletePizza(Integer id) {
        pizzaRepository.deletePizza(id);
    }

    @Override
    public PizzaType[] getAllPizzaTypes() {
        return pizzaRepository.getAllPizzaTypes();
    }
}
