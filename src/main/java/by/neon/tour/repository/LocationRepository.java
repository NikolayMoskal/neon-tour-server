package by.neon.tour.repository;

import by.neon.tour.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT l FROM CountryTo c JOIN c.locations l WHERE c.countryName = :country")
    List<Location> getLocationByCountry(@Param("country") String country);
}
