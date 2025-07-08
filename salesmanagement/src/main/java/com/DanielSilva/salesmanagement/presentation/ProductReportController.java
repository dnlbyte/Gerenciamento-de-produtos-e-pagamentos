package com.DanielSilva.salesmanagement.presentation;

import com.DanielSilva.salesmanagement.domain.model.ProductReport;
import com.DanielSilva.salesmanagement.application.service.ProductReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-reports")
public class ProductReportController {
    @Autowired
    private ProductReportService productReportService;

    @GetMapping
    public List<ProductReport> getAll(@RequestParam(value = "reportType", required = false) String reportType) {
        if (reportType != null && !reportType.isEmpty()) {
            return productReportService.findByReportType(reportType);
        }
        return productReportService.findAll();
    }

    @PostMapping
    public ProductReport create(@RequestBody ProductReport report) {
        return productReportService.save(report);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("reportType") String reportType) {
        List<ProductReport> reports = productReportService.findByReportType(reportType);
        if (reports.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productReportService.deleteByReportType(reportType);
        return ResponseEntity.noContent().build();
    }
} 