package by.neon.tour.service;

import by.neon.tour.model.Client;
import by.neon.tour.model.Order;
import by.neon.tour.model.Tour;

import java.util.List;

public interface OrderService {
    /**
     * @param id
     * @return
     */
    Order getById(int id);

    /**
     * @param order
     * @return
     */
    Order save(Order order);

    /**
     * @param id
     */
    void delete(int id);

    /**
     *
     */
    void deleteAll();

    /**
     * @param client
     */
    void deleteAllOrdersByClient(Client client);

    /**
     * @param client
     * @return
     */
    List<Order> getAllOrders(Client client);

    /**
     * @param tour
     * @return
     */
    List<Order> getById(Tour tour);
}
