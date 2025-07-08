package com.DanielSilva.salesmanagement.presentation;

import com.DanielSilva.salesmanagement.domain.model.Product;
import com.DanielSilva.salesmanagement.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(@RequestParam(value = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return productService.findByName(name);
        }
        return productService.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestParam("name") String name, @RequestBody Product product) {
        List<Product> products = productService.findByName(name);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product existing = products.get(0);
        product.setIdProduct(existing.getIdProduct());
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("name") String name) {
        List<Product> products = productService.findByName(name);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
} 