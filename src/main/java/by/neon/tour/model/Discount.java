package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nikolay Moskal
 */
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dscnt_id")
    private int id;

    @Column(name = "code", length = 20)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", referencedColumnName = "type")
    @JsonIgnore
    private DiscountType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discount", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Tour> tours;

    /**
     * Builds a new object of Discount
     */
    public Discount() {
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    public DiscountType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(DiscountType type) {
        this.type = type;
    }
}
