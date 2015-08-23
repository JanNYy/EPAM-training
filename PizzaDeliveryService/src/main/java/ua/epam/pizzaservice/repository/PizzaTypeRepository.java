package ua.epam.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.epam.pizzaservice.domain.PizzaTypeSeparate;
import ua.epam.pizzaservice.domain.PizzaType;

import java.util.List;

/**
 * Created by Anna on 21.08.2015.
 */
@Deprecated
public interface PizzaTypeRepository {

    PizzaTypeSeparate getPizzaTypeByEnum(PizzaType typeEnum);

    List<PizzaType> getAllPizzaTypes();
}
