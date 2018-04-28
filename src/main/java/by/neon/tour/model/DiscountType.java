package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.*;

/**
 * @author Nikolay Moskal
 */
@Entity
@Table(name = "discount_types")
public class DiscountType {
    @Id
    @Column(name = "type", length = 30)
    private String type;

    @Column(name = "value")
    private int value;

    @Column(name = "description", length = 200)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Discount> discounts;

    /**
     * Builds a new object of DiscountType
     */
    public DiscountType() {
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the discounts
     */
    public Set<Discount> getDiscounts() {
        return discounts;
    }

    /**
     * @param discounts the discounts to set
     */
    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }
}
