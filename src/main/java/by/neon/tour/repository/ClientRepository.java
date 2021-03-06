package by.neon.tour.repository;

import by.neon.tour.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT u FROM Client u WHERE LOWER(u.firstName) = LOWER(:name)")
    Client findByFirstName(@Param("name") String firstName);

    @Query("SELECT u FROM Client u WHERE LOWER(u.lastName) = LOWER(:name)")
    Client findByLastName(@Param("name") String lastName);

    @Query("SELECT u FROM Client u WHERE LOWER(u.firstName) = LOWER(:first) AND LOWER(u.lastName) = LOWER(:last)")
    Client findByFullName(@Param("first") String firstName, @Param("last") String lastName);
}
