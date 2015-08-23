package ua.epam.pizzaservice.domain;

import javax.persistence.*;

/**
 * Created by Anna on 23.07.2015.
 */
@Entity
@Table(name = "pizza")
//@Component("pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private Integer id;

    @Column(name = "name_pizza", nullable = false)
    private String name;

    @Column(name = "price_pizza", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_pizza", nullable = false)
    private PizzaType type;

//    @ManyToOne
//    @JoinColumn(name = "id_pizza_type_FK", referencedColumnName = "id_pizza_type")
//    private PizzaTypeSeparate type;

    public Pizza() {
    }

    public Pizza(String name, Double price, PizzaType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pizza))
            return false;

        Pizza pizza = (Pizza) o;

        if (id != null
                ? !id.equals(pizza.id)
                : pizza.id != null)
            return false;
        if (name != null
                ? !name.equals(pizza.name)
                : pizza.name != null)
            return false;
        if (price != null
                ? !price.equals(pizza.price)
                : pizza.price != null)
            return false;
        return type == pizza.type;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
