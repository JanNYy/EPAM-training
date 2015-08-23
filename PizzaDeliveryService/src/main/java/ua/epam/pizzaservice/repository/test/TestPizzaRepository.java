package ua.epam.pizzaservice.repository.test;

import ua.epam.pizzaservice.domain.Pizza;
import ua.epam.pizzaservice.domain.PizzaTypeSeparate;
import ua.epam.pizzaservice.domain.PizzaType;
import ua.epam.pizzaservice.infrastructure.Benchmark;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anna on 23.07.2015.
 */
//@Repository
public class TestPizzaRepository /*implements PizzaRepository*/ {

    private List<Pizza> pizzaList;

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

//    @Override
    public List<Pizza> getAllPizzas() {
        return null;
    }

//    @Override
    public Pizza getPizzaByID(Integer id) {
        return null;
    }

    @Benchmark
    public Pizza getPizzaByID(int id) {
        for (Pizza p : pizzaList)
        {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

//    @Override
    public Integer savePizza(Pizza pizza) {
        return 0;
    }

    @PostConstruct
    public void init() {
        pizzaList = Arrays.asList(
                new Pizza("Margarita", 80.0, PizzaType.Vegetarian),
                new Pizza("Americana", 100.0, PizzaType.Meat),
                new Pizza("Sea", 120.0, PizzaType.Sea));
    }

    @Override
    public String toString() {
        return "TestPizzaRepository";
    }
}