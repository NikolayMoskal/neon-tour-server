package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country_from")
public class CountryFrom {
    @Id
    @Column(name = "cntr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 20)
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency", referencedColumnName = "crn_name")
    private Currency currency;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryFrom", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CityFrom> cities;

    /**
     * Builds a new object of CountryFrom
     */
    public CountryFrom() {
    }

    public Set<CityFrom> getCities() {
        return cities;
    }

    public void setCities(Set<CityFrom> cities) {
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
