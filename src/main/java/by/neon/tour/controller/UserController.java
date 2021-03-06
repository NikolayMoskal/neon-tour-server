/**
 * UserController.java
 */
package by.neon.tour.controller;

import by.neon.tour.model.AuthUser;
import by.neon.tour.model.JwtUser;
import by.neon.tour.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nikolay Moskal
 */
@RestController
@RequestMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/get/all"}, method = RequestMethod.GET)
    public List<AuthUser> getAllUsers() {
        logger.info("Getting all users...");
        return userService.getAll();
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public AuthUser getByUsername(@RequestParam(name = "name") String username) {
        if (username.equals("")) {
            logger.error("Empty user name!");
            return null;
        }
        AuthUser user = userService.getByUsername(username);
        if (user == null) {
            logger.error("User by username \"" + username + "\" isn't found.");
            return null;
        }
        logger.info("The user is found. Send reply from server.");
        return user;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public AuthUser addUser(@RequestBody AuthUser user) {
        AuthUser exist = userService.getByUsername(user.getUsername());
        if (exist != null) {
            logger.error("The user is already exists.");
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        AuthUser created = userService.save(user);
        logger.info("The user is created successfully.");
        userService.saveWithAuthorities(new JwtUser(created.getUsername(), "ROLE_USER"));
        return created;
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.PUT)
    public AuthUser updateUser(@RequestBody AuthUser user) {
        AuthUser exist = userService.getById(user.getId());
        if (exist == null) {
            logger.error("Unable to update. The user by username \"" + user.getUsername() + "\" isn't found.");
            return null;
        }
        exist.setPassword(passwordEncoder.encode(user.getPassword()));
        exist.setEnabled(user.isEnabled());
        AuthUser updated = userService.save(exist);
        logger.info("The user was updated successfully.");
        return updated;
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam(name = "id") int id) {
        AuthUser exist = userService.getById(id);
        if (exist == null) {
            logger.error("Unable to delete. The user isn't found.");
            return;
        }
        userService.delete(exist);
        logger.info("The user by username \"" + exist.getUsername() + "\" was deleted successfully.");
    }

    @RequestMapping(value = {"/delete/all"}, method = RequestMethod.DELETE)
    public void deleteAllUsers() {
        logger.info("Deleting all users...");
        userService.deleteAll();
    }
}
