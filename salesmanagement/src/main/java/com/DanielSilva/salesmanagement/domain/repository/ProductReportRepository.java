package com.DanielSilva.salesmanagement.domain.repository;

import com.DanielSilva.salesmanagement.domain.model.ProductReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductReportRepository extends JpaRepository<ProductReport, Integer> {
    List<ProductReport> findByReportTypeContainingIgnoreCase(String reportType);
} 