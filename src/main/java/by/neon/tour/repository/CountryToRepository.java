package by.neon.tour.repository;

import by.neon.tour.model.CountryTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryToRepository extends JpaRepository<CountryTo, Integer> {
}
