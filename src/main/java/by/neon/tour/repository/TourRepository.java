package by.neon.tour.repository;

import by.neon.tour.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    @Query("SELECT e FROM Tour e WHERE LOWER(e.name) = LOWER(:name)")
    Tour findByName(@Param("name") String name);

    @Query("SELECT o FROM Tour o WHERE o.startDate >= :startDate")
    List<Tour> getFromDate(@Param("startDate") Date date);

    @Query("SELECT o FROM Tour o WHERE o.endDate <= :endDate")
    List<Tour> getToDate(@Param("endDate") Date date);

    @Query("SELECT o FROM Tour o WHERE o.startDate >= :startDate AND o.endDate <= :endDate")
    List<Tour> getBetweenDates(@Param("startDate") Date date1,
                               @Param("endDate") Date date2);

    @Query("SELECT o FROM Hotel h JOIN h.tours o WHERE h.hotelName = :hotel")
    List<Tour> getByHotel(@Param("hotel") String hotel);

    @Query("SELECT o FROM Tour o WHERE o.price >= :price")
    List<Tour> getFromPrice(@Param("price") int price);

    @Query("SELECT o FROM Tour o WHERE o.price <= :price")
    List<Tour> getToPrice(@Param("price") int price);

    @Query("SELECT o FROM Tour o WHERE o.price >= :price1 AND o.price <= :price2")
    List<Tour> getBetweenPrices(@Param("price1") int from,
                                @Param("price2") int to);
}
