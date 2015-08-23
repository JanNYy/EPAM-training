package ua.epam.pizzaservice.infrastructure;

/**
 * Created by Anna on 23.07.2015.
 */
public enum ObjectFactory {

    instance;

    private Config config = new JavaConfig();

    public Object createObject(String name) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = config.getImplementation(name);
        return clazz.newInstance();
    }
}
