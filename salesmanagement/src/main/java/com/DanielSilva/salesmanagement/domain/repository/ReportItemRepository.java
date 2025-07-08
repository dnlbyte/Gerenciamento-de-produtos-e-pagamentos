package com.DanielSilva.salesmanagement.domain.repository;

import com.DanielSilva.salesmanagement.domain.model.ReportItem;
import com.DanielSilva.salesmanagement.domain.model.ReportItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportItemRepository extends JpaRepository<ReportItem, ReportItemId> {
} 