package com.DanielSilva.salesmanagement.domain.repository;

import com.DanielSilva.salesmanagement.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
} 