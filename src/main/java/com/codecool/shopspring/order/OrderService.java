package com.codecool.shopspring.order;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findOrderById(final Long id) {
        return repository.findById(id);
    }

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(long id, Order order) {
        final var optionalOrder = repository.findById(id);
        final var orderToSave = optionalOrder.map(p -> updateOrderFields(p, order)).orElse(order);
        return repository.save(orderToSave);
    }

    private Order updateOrderFields(Order current, Order updates) {
        current.setTitle(updates.getTitle());
        current.setProducts(updates.getProducts());
        return current;
    }

    public void deleteOrder(long id) {
        repository.deleteById(id);
    }
}
