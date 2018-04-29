package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * @author Nikolay Moskal
 */
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "birthdate")
    private Date birthDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<AuthUser> authUsers;

    /**
     * Builds a new object of Client
     */
    public Client() {
    }

    public Set<AuthUser> getAuthUsers() {
        return authUsers;
    }

    public void setAuthUsers(Set<AuthUser> authUsers) {
        this.authUsers = authUsers;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the orders
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param date the birthdate in milliseconds to set
     */
    public void setBirthDate(long date) {
        this.birthDate = new Date(date);
    }

    /**
     * @param birthDate the birthdate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
