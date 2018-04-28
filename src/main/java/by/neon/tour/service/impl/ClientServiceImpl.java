package by.neon.tour.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.neon.tour.model.Client;
import by.neon.tour.model.Order;
import by.neon.tour.repository.ClientRepository;
import by.neon.tour.service.ClientService;

/**
 * @author Nikolay Moskal
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#getAllOrders(by.neon.tour.model.Client)
     */
    @Override
    public List<Order> getAllOrders(Client client) {
        return clientRepository.findAllOrders(client);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#getById(int)
     */
    @Override
    public Client getById(int id) {
        return clientRepository.findById(id).get();
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#getByFirstName(java.lang.String)
     */
    @Override
    public Client getByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#getByLastName(java.lang.String)
     */
    @Override
    public Client getByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#getByFullName(java.lang.String, java.lang.String)
     */
    @Override
    public Client getByFullName(String firstName, String lastName) {
        return clientRepository.findByFullName(firstName, lastName);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#save(by.neon.tour.model.Client)
     */
    @Override
    public Client save(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#getAllClients()
     */
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#deleteClient(by.neon.tour.model.Client)
     */
    @Override
    public void deleteClient(Client client) {
        clientRepository.deleteById(client.getId());
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.ClientService#deleteAll()
     */
    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

}
