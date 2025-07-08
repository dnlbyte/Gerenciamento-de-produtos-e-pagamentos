package com.DanielSilva.salesmanagement.domain.repository;

import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
} 