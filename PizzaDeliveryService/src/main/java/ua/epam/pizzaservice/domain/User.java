package ua.epam.pizzaservice.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Anna on 23.08.2015.
 */
@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(name = "name_user")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean isEnabled;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_FK", referencedColumnName = "id_user")
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String password, Boolean isEnabled) {
        this.name = name;
        this.password = password;
        this.isEnabled = isEnabled;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;

        User user = (User) o;

        if (id != null
                ? !id.equals(user.id)
                : user.id != null)
            return false;
        if (name != null
                ? !name.equals(user.name)
                : user.name != null)
            return false;
        if (password != null
                ? !password.equals(user.password)
                : user.password != null)
            return false;
        if (isEnabled != null
                ? !isEnabled.equals(user.isEnabled)
                : user.isEnabled != null)
            return false;
        return !(roles != null
                ? !roles.equals(user.roles)
                : user.roles != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isEnabled != null ? isEnabled.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
