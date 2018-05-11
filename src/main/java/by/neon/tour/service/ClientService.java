package by.neon.tour.service;

import by.neon.tour.model.Client;

import java.util.List;

/**
 * @author Nikolay Moskal
 */
public interface ClientService {
    /**
     * @param id
     * @return Client
     */
    Client getById(int id);

    /**
     * @param firstName
     * @return Client
     */
    Client getByFirstName(String firstName);

    /**
     * @param lastName
     * @return Client
     */
    Client getByLastName(String lastName);

    /**
     * @param firstName
     * @param lastName
     * @return Client
     */
    Client getByFullName(String firstName, String lastName);

    /**
     * @param client
     * @return Client
     */
    Client save(Client client);

    /**
     * @return List
     */
    List<Client> getAllClients();

    /**
     * @param client
     */
    void deleteClient(Client client);

    /**
     *
     */
    void deleteAll();
}
