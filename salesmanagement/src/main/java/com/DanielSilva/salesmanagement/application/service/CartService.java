package com.DanielSilva.salesmanagement.application.service;

import com.DanielSilva.salesmanagement.domain.model.Order;
import com.DanielSilva.salesmanagement.domain.model.OrderItem;
import com.DanielSilva.salesmanagement.domain.model.Product;
import com.DanielSilva.salesmanagement.domain.repository.OrderRepository;
import com.DanielSilva.salesmanagement.domain.repository.OrderItemRepository;
import com.DanielSilva.salesmanagement.domain.repository.ProductRepository;
import com.DanielSilva.salesmanagement.domain.repository.UserRepository;
import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Order getOpenCartByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) return null;
        return orderRepository.findByUser(user).stream()
                .filter(o -> "ABERTO".equalsIgnoreCase(o.getState()))
                .findFirst().orElse(null);
    }

    public Order addItemToCart(String username, String productName, int quantity) {
        Order cart = getOpenCartByUsername(username);
        if (cart == null) {
            cart = new Order();
            cart.setUser(userRepository.findByUsername(username));
            cart.setState("ABERTO");
            cart = orderRepository.save(cart);
        }
        List<Product> products = productRepository.findByNameContainingIgnoreCase(productName);
        if (products.isEmpty()) return cart;
        Product product = products.get(0);
        Optional<OrderItem> existing = cart.getItems().stream()
                .filter(i -> i.getProduct().getIdProduct().equals(product.getIdProduct()))
                .findFirst();
        if (existing.isPresent()) {
            OrderItem item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
            orderItemRepository.save(item);
        } else {
            OrderItem item = new OrderItem();
            item.setOrder(cart);
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUnitPrice(product.getUnitPrice());
            orderItemRepository.save(item);
        }
        return orderRepository.findById(cart.getIdOrder()).orElse(cart);
    }

    public Order updateItemQuantity(String username, String productName, int quantity) {
        Order cart = getOpenCartByUsername(username);
        if (cart == null) return null;
        List<Product> products = productRepository.findByNameContainingIgnoreCase(productName);
        if (products.isEmpty()) return cart;
        Product product = products.get(0);
        Optional<OrderItem> existing = cart.getItems().stream()
                .filter(i -> i.getProduct().getIdProduct().equals(product.getIdProduct()))
                .findFirst();
        if (existing.isPresent()) {
            OrderItem item = existing.get();
            item.setQuantity(quantity);
            orderItemRepository.save(item);
        }
        return orderRepository.findById(cart.getIdOrder()).orElse(cart);
    }

    public Order removeItemFromCart(String username, String productName) {
        Order cart = getOpenCartByUsername(username);
        if (cart == null) return null;
        List<Product> products = productRepository.findByNameContainingIgnoreCase(productName);
        if (products.isEmpty()) return cart;
        Product product = products.get(0);
        cart.getItems().removeIf(i -> i.getProduct().getIdProduct().equals(product.getIdProduct()));
        orderRepository.save(cart);
        return orderRepository.findById(cart.getIdOrder()).orElse(cart);
    }

    public Order finalizeCart(String username) {
        Order cart = getOpenCartByUsername(username);
        if (cart == null) return null;
        cart.setState("FINALIZADO");
        return orderRepository.save(cart);
    }
} 