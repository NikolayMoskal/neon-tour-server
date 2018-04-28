package by.neon.tour.repository;

import by.neon.tour.model.HotelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<HotelCategory, Integer> {
}
