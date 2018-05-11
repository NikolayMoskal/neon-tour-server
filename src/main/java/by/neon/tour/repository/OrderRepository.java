package by.neon.tour.repository;

import by.neon.tour.model.Client;
import by.neon.tour.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Client u JOIN u.orders o WHERE u = :user")
    List<Order> findAllOrders(@Param("user") Client user);

    @Query("DELETE FROM Order o WHERE o.client = :client")
    void deleteAllOrdersByClient(@Param("client") Client client);

    @Query("SELECT o FROM Tour t JOIN t.orders o WHERE t.id = :tour")
    List<Order> findByTour(@Param("tour") int id);
}
