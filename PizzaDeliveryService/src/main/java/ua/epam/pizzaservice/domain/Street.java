package ua.epam.pizzaservice.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anna on 19.08.2015.
 */
@Entity
@Table(name = "street")
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_street")
    private Integer id;

    @Column(name = "name_street", unique = true, nullable = false)
    private String name;

    public Street() {
    }

    public Street(String name) {
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
        if (!(o instanceof Street))
            return false;

        Street street = (Street) o;

        if (id != null
                ? !id.equals(street.id)
                : street.id != null)
            return false;
        return !(name != null
                ? !name.equals(street.name)
                : street.name != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
