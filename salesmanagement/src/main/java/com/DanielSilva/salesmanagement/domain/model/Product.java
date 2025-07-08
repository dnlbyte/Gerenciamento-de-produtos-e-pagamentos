package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "name", length = 256, nullable = false)
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "tags", length = 256)
    private String tags;

    // Getters e setters
    public Integer getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
    public Double getUnitPrice() {
        return unitPrice;
    }
    // ...
} 