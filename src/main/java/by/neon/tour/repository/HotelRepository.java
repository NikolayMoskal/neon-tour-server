package by.neon.tour.repository;

import by.neon.tour.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT o FROM Location c JOIN c.hotels o WHERE c.shortDescription = :location")
    List<Hotel> getHotelsByLocation(@Param("location") String location);

    @Query("SELECT o FROM HotelCategory c JOIN c.hotels o WHERE c.description = :category")
    List<Hotel> getHotelsByCategory(@Param("category") String category);

    @Query(value = "SELECT h.* FROM (hotel h INNER JOIN location l ON h.lctn_id = l.lctn_id) INNER JOIN category c ON h.cat_id = c.cat_id WHERE l.short_descr = :location AND c.description = :category", nativeQuery = true)
    List<Hotel> getHotelsByCategoryAndLocation(@Param("category") String category,
                                               @Param("location") String location);
}
