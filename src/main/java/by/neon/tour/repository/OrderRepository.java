package by.neon.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.neon.tour.model.Order;
import org.springframework.stereotype.Repository;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
