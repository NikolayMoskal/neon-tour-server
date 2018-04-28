package by.neon.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.neon.tour.model.JwtUser;

/**
 * @author Nikolay Moskal
 */
@Repository
public interface UserRepository extends JpaRepository<JwtUser, Integer> {
    /**
     * Finds user by user name
     *
     * @param username the user name
     * @return {@link JwtUser}
     */
    @Query("SELECT s FROM JwtUser s WHERE s.username = :username")
    List<JwtUser> findByUserName(@Param("username") String username);
}
