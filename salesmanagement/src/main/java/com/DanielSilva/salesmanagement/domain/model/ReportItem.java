package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "report_item")
@IdClass(ReportItemId.class)
public class ReportItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_report")
    private ProductReport report;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "total_sold")
    private Integer totalSold;

    @Column(name = "total_revenue")
    private Double totalRevenue;

    // Getters e setters
    // ...
} 