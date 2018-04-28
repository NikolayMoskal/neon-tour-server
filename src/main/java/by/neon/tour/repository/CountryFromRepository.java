package by.neon.tour.repository;

import by.neon.tour.model.CountryFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryFromRepository extends JpaRepository<CountryFrom, Integer> {
}
