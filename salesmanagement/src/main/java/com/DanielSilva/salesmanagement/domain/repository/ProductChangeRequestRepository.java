package com.DanielSilva.salesmanagement.domain.repository;

import com.DanielSilva.salesmanagement.domain.model.ProductChangeRequest;
import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductChangeRequestRepository extends JpaRepository<ProductChangeRequest, Integer> {
    List<ProductChangeRequest> findByStatusContainingIgnoreCase(String status);
    List<ProductChangeRequest> findByEmployee(UserModel employee);
} 