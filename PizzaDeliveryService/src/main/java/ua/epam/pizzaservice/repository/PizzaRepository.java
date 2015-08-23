package ua.epam.pizzaservice.repository;

import ua.epam.pizzaservice.domain.Pizza;
import ua.epam.pizzaservice.domain.PizzaType;

import java.util.List;

/**
 * Created by Anna on 23.07.2015.
 */
public interface PizzaRepository {

    List<Pizza> getAllPizzas();

    Pizza getPizzaByID(Integer id);

    Integer savePizza(Pizza pizza);

    void deletePizza(Integer id);

    PizzaType[] getAllPizzaTypes();
}
