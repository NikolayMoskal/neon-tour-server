package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country_to")
public class CountryTo {
    @Id
    @Column(name = "cntr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50)
    private String countryName;

    @Column(name = "capital", length = 20)
    private String capital;

    @Column(name = "population", length = 30)
    private String nationality;

    @Column(name = "language", length = 20)
    private String language;

    @Column(name = "currency", length = 30)
    private String currency;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryTo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Location> locations;

    public CountryTo() {
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
