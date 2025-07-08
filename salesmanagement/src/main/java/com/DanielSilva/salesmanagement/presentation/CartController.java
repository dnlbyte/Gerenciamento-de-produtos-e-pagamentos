package com.DanielSilva.salesmanagement.presentation;

import com.DanielSilva.salesmanagement.domain.model.Order;
import com.DanielSilva.salesmanagement.application.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Order> getOpenCart(@RequestParam("username") String username) {
        Order cart = cartService.getOpenCartByUsername(username);
        if (cart == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addItem(@RequestParam("username") String username,
                                         @RequestParam("productName") String productName,
                                         @RequestParam("quantity") int quantity) {
        Order cart = cartService.addItemToCart(username, productName, quantity);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateQuantity(@RequestParam("username") String username,
                                                @RequestParam("productName") String productName,
                                                @RequestParam("quantity") int quantity) {
        Order cart = cartService.updateItemQuantity(username, productName, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Order> removeItem(@RequestParam("username") String username,
                                            @RequestParam("productName") String productName) {
        Order cart = cartService.removeItemFromCart(username, productName);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/finalize")
    public ResponseEntity<Order> finalizeCart(@RequestParam("username") String username) {
        Order cart = cartService.finalizeCart(username);
        return ResponseEntity.ok(cart);
    }
} 