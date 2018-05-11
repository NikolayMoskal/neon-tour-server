package by.neon.tour.service.impl;

import by.neon.tour.model.Client;
import by.neon.tour.model.Order;
import by.neon.tour.model.Tour;
import by.neon.tour.repository.OrderRepository;
import by.neon.tour.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getById(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getById(Tour tour) {
        return orderRepository.findByTour(tour.getId());
    }

    @Override
    public Order save(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }


    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    /**
     * (non-Javadoc)
     *
     * @see by.neon.tour.service.OrderService#getAllOrders(by.neon.tour.model.Client)
     */
    @Override
    public List<Order> getAllOrders(Client client) {
        return orderRepository.findAllOrders(client);
    }

    @Override
    public void deleteAllOrdersByClient(Client client) {
        orderRepository.deleteAllOrdersByClient(client);
    }
}
