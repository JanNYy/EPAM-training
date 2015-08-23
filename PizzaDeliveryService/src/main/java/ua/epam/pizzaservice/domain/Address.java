package ua.epam.pizzaservice.domain;

import javax.persistence.*;

/**
 * Created by Anna on 04.08.2015.
 */
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_city_FK",referencedColumnName = "id_city")
    private City city;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_street_FK", referencedColumnName = "id_street")
    private Street street;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "apartment")
    private String apartment;

    public Address() {
    }

    public Address(City city, Street street, String building, String apartment) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Address))
            return false;

        Address address = (Address) o;

        if (id != null
                ? !id.equals(address.id)
                : address.id != null)
            return false;
        if (city != null
                ? !city.equals(address.city)
                : address.city != null)
            return false;
        if (street != null
                ? !street.equals(address.street)
                : address.street != null)
            return false;
        if (building != null
                ? !building.equals(address.building)
                : address.building != null)
            return false;
        return !(apartment != null
                ? !apartment.equals(address.apartment)
                : address.apartment != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city=" + city +
                ", street=" + street +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
