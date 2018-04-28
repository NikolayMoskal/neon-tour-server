package by.neon.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.neon.tour.model.Tour;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    @Query("SELECT e FROM Tour e WHERE e.name = :name")
    Tour findByName(@Param("name") String name);
}
