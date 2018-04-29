package by.neon.tour.service;

import by.neon.tour.model.AuthUser;
import by.neon.tour.model.JwtUser;

import java.util.List;

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
     * @param withClient
     * @return List
     */
    List<AuthUser> getAll(int withClient);

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
