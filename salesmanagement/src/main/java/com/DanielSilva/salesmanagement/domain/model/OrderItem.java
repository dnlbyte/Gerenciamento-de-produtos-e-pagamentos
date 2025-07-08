package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
public class OrderItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    // Getters e setters
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    // ...
} 