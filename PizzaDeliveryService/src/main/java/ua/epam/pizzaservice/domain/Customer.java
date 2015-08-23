package ua.epam.pizzaservice.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anna on 04.08.2015.
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "telephone")
    private String telephone = "";

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE/*, CascadeType.REMOVE*/}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address_customer_FK", referencedColumnName = "id_address")
    private Address address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}/*, mappedBy = "customer"*/)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer_FK")
    private AccumulativeCard accumulativeCard;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_customer_FK", referencedColumnName = "id_customer")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "id_user_FK", referencedColumnName = "id_user")
    private User user;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName,
                    String telephone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.address = address;
    }

    public Customer(String firstName, String lastName,
                    String telephone, Address address,
                    AccumulativeCard accumulativeCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.address = address;
        this.accumulativeCard = accumulativeCard;
    }

    public Customer(String firstName, String lastName,
                    String telephone, Address address,
                    AccumulativeCard accumulativeCard,
                    List<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.address = address;
        this.accumulativeCard = accumulativeCard;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AccumulativeCard getAccumulativeCard() {
        return accumulativeCard;
    }

    public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
        this.accumulativeCard = accumulativeCard;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customer))
            return false;

        Customer customer = (Customer) o;

        if (id != null
                ? !id.equals(customer.id)
                : customer.id != null)
            return false;
        if (firstName != null
                ? !firstName.equals(customer.firstName)
                : customer.firstName != null)
            return false;
        if (lastName != null
                ? !lastName.equals(customer.lastName)
                : customer.lastName != null)
            return false;
        if (telephone != null
                ? !telephone.equals(customer.telephone)
                : customer.telephone != null)
            return false;
        if (address != null
                ? !address.equals(customer.address)
                : customer.address != null)
            return false;
        if (accumulativeCard != null
                ? !accumulativeCard.equals(customer.accumulativeCard)
                : customer.accumulativeCard != null)
            return false;
        if (orders != null
                ? !orders.equals(customer.orders)
                : customer.orders != null)
            return false;
        return !(user != null
                ? !user.equals(customer.user)
                : customer.user != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (accumulativeCard != null ? accumulativeCard.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address=" + address +
                '}';
    }
}
