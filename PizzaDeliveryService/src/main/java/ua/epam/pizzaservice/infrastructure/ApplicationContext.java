package ua.epam.pizzaservice.infrastructure;

/**
 * Created by Anna on 23.07.2015.
 */
public interface ApplicationContext {

    Object getBean(String beanName) throws Exception;
}
