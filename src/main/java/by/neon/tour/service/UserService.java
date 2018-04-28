package by.neon.tour.service;

import java.util.List;

import by.neon.tour.model.AuthUser;
import by.neon.tour.model.JwtUser;

/**
 * @author Nikolay Moskal
 */
public interface UserService {
    /**
     * @param username
     * @return JwtUser
     */
    JwtUser findByName(String username);

    /**
     * @return JwtUser
     */
    JwtUser saveWithAuthorities(JwtUser user);

    /**
     * @return List
     */
    List<AuthUser> getAll();

    /**
     * @param id
     * @return AuthenticationUser
     */
    AuthUser getById(int id);

    /**
     * @param username
     * @return AuthenticationUser
     */
    AuthUser getByUsername(String username);

    /**
     * @param user
     * @return AuthenticationUser
     */
    AuthUser save(AuthUser user);

    /**
     * @param user
     */
    void delete(AuthUser user);

    /**
     *
     */
    void deleteAll();
}
