package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Integer idPayment;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "method", length = 50)
    private String method;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "id_order")
    private Order order;

    // Getters e setters
    public Integer getIdPayment() {
        return idPayment;
    }
    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    // ...
} 