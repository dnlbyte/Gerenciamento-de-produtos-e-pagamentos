package com.DanielSilva.salesmanagement.presentation;

import com.DanielSilva.salesmanagement.domain.model.Order;
import com.DanielSilva.salesmanagement.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAll(@RequestParam(value = "username", required = false) String username) {
        if (username != null && !username.isEmpty()) {
            return orderService.findByUsername(username);
        }
        return orderService.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("username") String username) {
        orderService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }
} 