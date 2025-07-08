package com.DanielSilva.salesmanagement.application.service;

import com.DanielSilva.salesmanagement.domain.model.ProductReport;
import com.DanielSilva.salesmanagement.domain.repository.ProductReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReportService {
    @Autowired
    private ProductReportRepository productReportRepository;

    public List<ProductReport> findAll() {
        return productReportRepository.findAll();
    }

    public List<ProductReport> findByReportType(String reportType) {
        return productReportRepository.findByReportTypeContainingIgnoreCase(reportType);
    }

    public ProductReport save(ProductReport report) {
        return productReportRepository.save(report);
    }

    public void deleteByReportType(String reportType) {
        List<ProductReport> reports = productReportRepository.findByReportTypeContainingIgnoreCase(reportType);
        reports.forEach(r -> productReportRepository.delete(r));
    }
} 