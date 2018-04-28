/**
 * IndexController.java
 */
package by.neon.tour.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.neon.tour.model.AuthUser;
import by.neon.tour.model.AuthorizationData;
import by.neon.tour.model.Client;
import by.neon.tour.model.Order;
import by.neon.tour.service.ClientService;

/**
 * @author Nikolay Moskal
 */
@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserController userController;

    @RequestMapping(value = {"/orders"}, method = RequestMethod.GET)
    public List<Order> getClientOrders(@RequestParam("id") int id) {
        logger.debug("Getting all orders by specified client ID...");
        List<Order> orders = clientService.getAllOrders(clientService.getById(id));
        if (orders == null) {
            logger.info("No orders found!");
            return new ArrayList<>();
        } else {
            logger.info("Sent the order list to client side");
            return orders;
        }
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Client getClientByName(@RequestParam(name = "first", required = false) String firstName,
                                  @RequestParam(name = "last", required = false) String lastName) {
        Client client = null;
        if (firstName != null && lastName != null) {
            logger.debug("Find client by specified first-name and last-name...");
            client = clientService.getByFullName(firstName, lastName);
        } else if (firstName != null) {
            logger.debug("Find client by only first-name...");
            client = clientService.getByFirstName(firstName);
        } else if (lastName != null) {
            logger.debug("Find client by only last-name");
            client = clientService.getByLastName(lastName);
        }
        if (client == null) {
            logger.info("No client found!");
            return new Client();
        } else {
            logger.info("The client is found. Send reply from the server...");
            return client;
        }
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public Client addUser(@RequestBody AuthorizationData data) {
        Client exist = clientService.getByFullName(data.getFirstName(), data.getLastName());
        if (exist != null) {
            logger.error("Client is already exists");
            return null;
        }
        Client client = new Client();
        client.setFirstName(data.getFirstName());
        client.setLastName(data.getLastName());
        client.setBirthDate(data.getBirthDate());
        client.setEmail(data.getEmail());
        Client created = clientService.save(client);
        logger.info("The client was created successfully.");
        AuthUser user = userController.addUser(new AuthUser(created.getId(), data.getUsername(), data.getPassword()));
        if (user != null)
            logger.info("The user for client was created successfully");
        return created;
    }

    @RequestMapping(value = {"/get/all"}, method = RequestMethod.GET)
    public List<Client> getAllClients() {
        logger.info("Getting all clients...");
        return clientService.getAllClients();
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.PUT)
    public Client updateUser(@RequestBody Client client) {
        Client exist = clientService.getById(client.getId());
        if (exist == null) {
            logger.error("Client by id " + client.getId() + " isn't found");
            return null;
        }
        exist.setFirstName(client.getFirstName());
        exist.setLastName(client.getLastName());
        exist.setBirthDate(client.getBirthDate());
        exist.setEmail(client.getEmail());
        Client updated = clientService.save(exist);
        logger.info("The client was updated successfully.");
        return updated;
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.DELETE)
    public void delete(@RequestBody Client client) {
        Client exist = clientService.getById(client.getId());
        if (exist == null) {
            logger.error("Unable to delete. Client by id " + client.getId() + " isn't found.");
            return;
        }
        clientService.deleteClient(exist);
        logger.info("The client by id " + exist.getId() + " was deleted successfully");
    }

    @RequestMapping(value = {"/delete/all"}, method = RequestMethod.DELETE)
    public void deleteAllClients() {
        logger.info("Deleting all clients...");
        clientService.deleteAll();
    }
}
