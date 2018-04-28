package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "lctn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "short_descr", length = 30)
    private String shortDescription;

    @Column(name = "description", length = 300)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cntr_id", referencedColumnName = "cntr_id")
    @JsonIgnore
    private CountryTo countryTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Hotel> hotels;

    public Location() {
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CountryTo getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(CountryTo countryTo) {
        this.countryTo = countryTo;
    }
}
