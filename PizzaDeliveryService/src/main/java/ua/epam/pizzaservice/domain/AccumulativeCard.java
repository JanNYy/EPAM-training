package ua.epam.pizzaservice.domain;

import javax.persistence.*;

/**
 * Created by Anna on 04.08.2015.
 */
@Entity
@Table(name = "accumulative_card")
public class AccumulativeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accumulative_card")
    private Integer id;

    @Column(name = "sum_accumulative_card", nullable = false)
    private Double sum;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_customer_FK", referencedColumnName = "id_customer")
    private Customer customer;

    public AccumulativeCard() {
    }

    public AccumulativeCard(Double sum, Customer customer) {
        this.sum = sum;
        this.customer = customer;
    }

    public AccumulativeCard(Double sum) {
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double addSum(Double sum) {
        this.sum += sum;
        return this.sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccumulativeCard)) return false;

        AccumulativeCard that = (AccumulativeCard) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (sum != null ? !sum.equals(that.sum) : that.sum != null)
            return false;
        return !(customer != null
                ? !customer.equals(that.customer)
                : that.customer != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccumulativeCard{" +
                "id=" + id +
                ", sum=" + sum +
                ", customer=" + customer +
                '}';
    }
}
