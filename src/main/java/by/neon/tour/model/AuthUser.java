package by.neon.tour.model;

import javax.persistence.*;

/**
 * @author Nikolay Moskal
 */
@Entity
@Table(name = "users")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    /**
     * Builds a new object of AuthenticationUser
     */
    public AuthUser() {
    }

    /**
     * Builds a new object of AuthenticationUser
     *
     * @param id
     * @param username
     * @param password
     */
    public AuthUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
}
