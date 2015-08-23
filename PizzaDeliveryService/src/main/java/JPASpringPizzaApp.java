import ua.epam.pizzaservice.domain.Pizza;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.pizzaservice.domain.PizzaTypeSeparate;
import ua.epam.pizzaservice.domain.PizzaType;
import ua.epam.pizzaservice.repository.PizzaRepository;

/**
 * Created by Anna on 06.08.2015.
 */
public class JPASpringPizzaApp {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("appContext.xml");

        PizzaRepository pizzaRepository = (PizzaRepository) applicationContext.getBean("pizzaRepository");

        for (Pizza p : pizzaRepository.getAllPizzas()) {
            System.out.println(p);
        }

//        PizzaService pizzaService = (PizzaService) applicationContext.getBean("pizzaService");
//        System.out.println(pizzaService.getAllPizzas());

        Pizza pizza = new Pizza();
        pizza.setName("New pizza");
        pizza.setPrice(100.0);
        pizza.setType(PizzaType.Meat);
        Integer id = pizzaRepository.savePizza(pizza);
        System.out.println(id);
    }
}
