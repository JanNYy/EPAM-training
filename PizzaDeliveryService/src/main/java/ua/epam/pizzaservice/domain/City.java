package ua.epam.pizzaservice.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anna on 19.08.2015.
 */
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Integer id;

    @Column(name = "name_city", unique = true, nullable = false, length = 50)
    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof City))
            return false;

        City city = (City) o;

        if (id != null
                ? !id.equals(city.id)
                : city.id != null)
            return false;
        return !(name != null
                ? !name.equals(city.name)
                : city.name != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
