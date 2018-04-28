package by.neon.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.neon.tour.model.Client;
import by.neon.tour.model.Order;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT u FROM Client u WHERE u.firstName = :name")
    Client findByFirstName(@Param("name") String firstName);

    @Query("SELECT u FROM Client u WHERE u.lastName = :name")
    Client findByLastName(@Param("name") String lastName);

    @Query("SELECT u FROM Client u WHERE u.firstName = :first AND u.lastName = :last")
    Client findByFullName(@Param("first") String firstName, @Param("last") String lastName);

    @Query("SELECT o FROM Client u JOIN u.orders o WHERE u = :user")
    List<Order> findAllOrders(@Param("user") Client user);
}
