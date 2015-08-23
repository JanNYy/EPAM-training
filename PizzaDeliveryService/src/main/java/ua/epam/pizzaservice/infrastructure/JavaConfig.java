package ua.epam.pizzaservice.infrastructure;

import ua.epam.pizzaservice.repository.test.TestOrderRepository;
import ua.epam.pizzaservice.repository.test.TestPizzaRepository;
import ua.epam.pizzaservice.service.test.SimpleOrderService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anna on 23.07.2015.
 */
public class JavaConfig implements Config {

    private final Map<String,Class<?>> map = new HashMap<String, Class<?>>();

    {
        map.put("pizzaRepository", TestPizzaRepository.class);
        map.put("orderRepository", TestOrderRepository.class);

        map.put("orderService", SimpleOrderService.class);
    }

    public Class<?> getImplementation(String beanName) {
        return map.get(beanName);
    }
}
