package com.DanielSilva.salesmanagement.security.domain.repository;

import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByUsernameAndActiveIsTrue(String username);
}
