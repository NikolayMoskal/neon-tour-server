package by.neon.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.neon.tour.model.AuthUser;
import by.neon.tour.model.JwtUser;
import by.neon.tour.repository.AuthUserRepository;
import by.neon.tour.repository.UserRepository;
import by.neon.tour.service.UserService;

/**
 * @author Nikolay Moskal
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthUserRepository authUserRepository;

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#findByName(java.lang.String)
     */
    @Override
    public JwtUser findByName(String username) throws UsernameNotFoundException {
        List<JwtUser> users = userRepository.findByUserName(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        List<String> roles = new ArrayList<>();
        for (JwtUser user : users)
            roles.add(user.getRole());
        String rolesList = String.join(",", roles);
        return new JwtUser(username, rolesList);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#saveWithAuthorities(by.neon.tour.model.JwtUser)
     */
    @Override
    public JwtUser saveWithAuthorities(JwtUser user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#getAll()
     */
    @Override
    public List<AuthUser> getAll() {
        return authUserRepository.findAll();
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#getById(int)
     */
    @Override
    public AuthUser getById(int id) {
        return authUserRepository.findById(id).get();
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#getByUsername(java.lang.String)
     */
    @Override
    public AuthUser getByUsername(String username) {
        return authUserRepository.getByUsername(username);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#save(by.neon.tour.model.AuthUser)
     */
    @Override
    public AuthUser save(AuthUser user) {
        return authUserRepository.saveAndFlush(user);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#delete(by.neon.tour.model.AuthUser)
     */
    @Override
    public void delete(AuthUser user) {
        authUserRepository.deleteById(user.getId());
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.UserService#deleteAll()
     */
    @Override
    public void deleteAll() {
        authUserRepository.deleteAll();
    }

}
