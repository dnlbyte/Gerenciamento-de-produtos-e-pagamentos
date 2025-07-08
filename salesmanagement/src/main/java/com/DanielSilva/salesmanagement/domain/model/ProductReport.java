package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product_report")
public class ProductReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_report")
    private Integer idReport;

    @Column(name = "report_type", length = 50)
    private String reportType;

    @Column(name = "period_start")
    private LocalDate periodStart;

    @Column(name = "period_end")
    private LocalDate periodEnd;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<ReportItem> items;

    // Getters e setters
    // ...
} 