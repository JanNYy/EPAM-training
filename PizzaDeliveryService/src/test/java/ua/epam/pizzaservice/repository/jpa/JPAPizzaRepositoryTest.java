package ua.epam.pizzaservice.repository.jpa;

import ua.epam.pizzaservice.domain.Pizza;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.pizzaservice.domain.PizzaTypeSeparate;
import ua.epam.pizzaservice.domain.PizzaType;
import ua.epam.pizzaservice.repository.PizzaRepository;
import ua.epam.pizzaservice.repository.template.ITRepositoryTestTemplate;

import static org.junit.Assert.*;

/**
 * Created by Anna on 06.08.2015.
 */
public class JPAPizzaRepositoryTest extends ITRepositoryTestTemplate {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void testSomeMethod() {
        Pizza pizza = new Pizza();
        pizza.setName("New pizza");
        pizza.setPrice(100.0);
        pizza.setType(PizzaType.Meat);
        Integer id = pizzaRepository.savePizza(pizza);
        assertNotNull(id);
    }

}