package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_change_request")
public class ProductChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request")
    private Integer idRequest;

    @Column(name = "request_type", length = 20)
    private String requestType;

    @Column(name = "product_data", columnDefinition = "TEXT")
    private String productData;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel employee;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private UserModel admin;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    // Getters e setters
    public Integer getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getProductData() {
        return productData;
    }

    public void setProductData(String productData) {
        this.productData = productData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getEmployee() {
        return employee;
    }

    public void setEmployee(UserModel employee) {
        this.employee = employee;
    }

    public UserModel getAdmin() {
        return admin;
    }

    public void setAdmin(UserModel admin) {
        this.admin = admin;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
} 