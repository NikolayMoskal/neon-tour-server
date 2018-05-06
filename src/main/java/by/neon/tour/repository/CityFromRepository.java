package by.neon.tour.repository;

import by.neon.tour.model.CityFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityFromRepository extends JpaRepository<CityFrom, Integer> {
    @Query("SELECT o FROM CountryFrom c JOIN c.cities o WHERE LOWER(c.name) = LOWER(:country)")
    List<CityFrom> findByCountry(@Param("country") String countryName);
}
