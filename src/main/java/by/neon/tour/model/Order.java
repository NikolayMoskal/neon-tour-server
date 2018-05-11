/**
 * Order.java
 */
package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Nikolay Moskal
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "notes", length = 300)
    private String notes;

    @Column(name = "state")
    private boolean stateOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", referencedColumnName = "tour_id")
    private Tour tour;

    /**
     * Builds a new object of Order
     */
    public Order() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStateOrder() {
        return stateOrder;
    }

    /**
     * @param stateOrder the stateOrder to set
     */
    public void setStateOrder(boolean stateOrder) {
        this.stateOrder = stateOrder;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
