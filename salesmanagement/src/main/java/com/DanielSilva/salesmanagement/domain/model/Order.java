package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "state", length = 20)
    private String state;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    // Getters e setters
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public Integer getIdOrder() {
        return idOrder;
    }
    // ...
} 