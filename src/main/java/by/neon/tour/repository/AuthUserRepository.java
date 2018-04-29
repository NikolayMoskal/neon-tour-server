package by.neon.tour.repository;

import by.neon.tour.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    @Query("SELECT u FROM AuthUser u WHERE u.username = :username")
    AuthUser getByUsername(@Param("username") String username);

    @Query(value = "SELECT u, c FROM AuthUser u INNER JOIN u.client c")
    List<Object[]> getAuthUsersWithClient();
}
