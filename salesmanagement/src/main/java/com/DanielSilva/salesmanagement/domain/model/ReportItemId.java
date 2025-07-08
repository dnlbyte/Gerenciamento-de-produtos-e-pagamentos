package com.DanielSilva.salesmanagement.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class ReportItemId implements Serializable {
    private Integer report;
    private Integer product;

    public ReportItemId() {}

    public ReportItemId(Integer report, Integer product) {
        this.report = report;
        this.product = product;
    }

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportItemId that = (ReportItemId) o;
        return Objects.equals(report, that.report) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(report, product);
    }
} 