package ua.epam.pizzaservice.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by Anna on 23.07.2015.
 */
@Entity
@Table(name = "`order`")
//@Component("order")
//@Scope("prototype")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime_order", nullable = false)
    private Date dateTime;

    @Column(name = "sum_order")
    private Double sum;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_customer_FK", referencedColumnName = "id_customer")
    private Customer customer;

    @ManyToOne(/*cascade = {CascadeType.PERSIST, CascadeType.MERGE}*/)
    @JoinColumn(name = "id_address_order_FK", referencedColumnName = "id_address")
    private Address address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pizza_in_order",
            joinColumns = @JoinColumn(name = "id_order_FK", referencedColumnName = "id_order"))
    @MapKeyJoinColumn(name = "id_pizza_FK",referencedColumnName = "id_pizza")
    @Column(name = "amount")
    private Map<Pizza, Integer> pizzas;

//    private List<Pizza> pizzas;

    public Order() {
    }

    public Order(Date dateTime, Customer customer,
                 Address address, Map<Pizza, Integer> pizzas) {
        this.dateTime = dateTime;
        this.customer = customer;
        this.address = address;
        this.pizzas = pizzas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Map<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
    }

    public void addPizza(Pizza pizza) {
        if (pizzas.containsKey(pizza))
            pizzas.put(pizza, pizzas.get(pizza)+1);
        else
            pizzas.put(pizza,1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;

        Order order = (Order) o;

        if (id != null
                ? !id.equals(order.id)
                : order.id != null)
            return false;
        if (dateTime != null
                ? !dateTime.equals(order.dateTime)
                : order.dateTime != null)
            return false;
        if (sum != null
                ? !sum.equals(order.sum)
                : order.sum != null)
            return false;
        if (customer != null
                ? !customer.equals(order.customer)
                : order.customer != null)
            return false;
        if (address
                != null ? !address.equals(order.address)
                : order.address != null)
            return false;
        return !(pizzas != null
                ? !pizzas.equals(order.pizzas)
                : order.pizzas != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (pizzas != null ? pizzas.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", sum=" + sum +
                ", customer=" + customer +
                ", address=" + address +
                ", pizzas=" + pizzas +
                '}';
    }

//    public void destroy() {
//        System.out.println("destroy");
//    }
}
