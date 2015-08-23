package ua.epam.pizzaservice.web;

import ua.epam.pizzaservice.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ua.epam.pizzaservice.service.*;

import java.beans.PropertyEditorSupport;

/**
 * Created by Anna on 14.08.2015.
 */
public abstract class AbstractController {

    @Autowired
    protected PizzaService pizzaService;

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected AccumulativeCardService accumulativeCardService;

    @Autowired
    protected CityService cityService;

    protected Pizza getPizzaByID(Integer id)
    {
        if (id <= 0)
        {
            throw new IllegalArgumentException("Pizza id < 0");
        }
        Pizza pizza = pizzaService.getPizzaByID(id);
        if (pizza == null)
        {
            throw new NotFoundPizzaException("Pizza id " + id + " not found");
        }
        return pizza;
    }

    @InitBinder
    private void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String pizzaId) {
                        Pizza pizza = null;
                        if (pizzaId != null && !pizzaId.trim().isEmpty())
                        {
                            Integer id = Integer.valueOf(pizzaId);
                            pizza = getPizzaByID(id);
                        }
                        setValue(pizza);
                    }
                }
        );
    }
}
