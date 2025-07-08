package com.DanielSilva.salesmanagement.application.service;

import com.DanielSilva.salesmanagement.domain.model.Product;
import com.DanielSilva.salesmanagement.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        products.forEach(p -> productRepository.delete(p));
    }
} 