package by.neon.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "city_from")
public class CityFrom {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 20)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cntr_id", referencedColumnName = "cntr_id")
    @JsonIgnore
    private CountryFrom countryFrom;

    public CityFrom() {
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

    public CountryFrom getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(CountryFrom countryFrom) {
        this.countryFrom = countryFrom;
    }
}
