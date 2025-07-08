package com.DanielSilva.salesmanagement.application.service;

import com.DanielSilva.salesmanagement.domain.model.Order;
import com.DanielSilva.salesmanagement.domain.repository.OrderRepository;
import com.DanielSilva.salesmanagement.domain.repository.UserRepository;
import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) return List.of();
        return orderRepository.findByUser(user);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user != null) {
            List<Order> orders = orderRepository.findByUser(user);
            orders.forEach(o -> orderRepository.delete(o));
        }
    }
} 