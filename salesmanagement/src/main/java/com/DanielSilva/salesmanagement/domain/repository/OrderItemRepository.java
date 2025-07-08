package com.DanielSilva.salesmanagement.domain.repository;

import com.DanielSilva.salesmanagement.domain.model.OrderItem;
import com.DanielSilva.salesmanagement.domain.model.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
} 