package ua.epam.pizzaservice.service;

import ua.epam.pizzaservice.domain.Pizza;
import ua.epam.pizzaservice.domain.PizzaTypeSeparate;
import ua.epam.pizzaservice.domain.PizzaType;

import java.util.List;

/**
 * Created by Anna on 07.08.2015.
 */
public interface PizzaService {

    List<Pizza> getAllPizzas();

    Pizza getPizzaByID(Integer id);

    Integer savePizza(Pizza pizza);

    void deletePizza(Integer id);

    PizzaType[] getAllPizzaTypes();
}
