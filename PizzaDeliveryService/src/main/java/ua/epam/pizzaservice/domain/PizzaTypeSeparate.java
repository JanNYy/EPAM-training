package ua.epam.pizzaservice.domain;

import javax.persistence.*;

/**
 * Created by Anna on 20.08.2015.
 */
@Deprecated
//@Entity
//@Table(name = "pizza_type")
public class PizzaTypeSeparate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza_type")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_pizza_type", unique = true,
            insertable = false, updatable = false)
    private PizzaType name;

    public PizzaTypeSeparate() {
    }

    public PizzaTypeSeparate(PizzaType name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public PizzaType getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PizzaTypeSeparate))
            return false;

        PizzaTypeSeparate pizzaTypeSeparate = (PizzaTypeSeparate) o;

        if (!id.equals(pizzaTypeSeparate.id))
            return false;
        return name.equals(pizzaTypeSeparate.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

}
