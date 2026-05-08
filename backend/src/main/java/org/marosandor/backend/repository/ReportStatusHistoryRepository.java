package org.marosandor.backend.repository;

import org.marosandor.backend.entity.ReportStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportStatusHistoryRepository extends JpaRepository<ReportStatusHistory, Long> {
    List<ReportStatusHistory> findByReportIdOrderByChangedAtDesc(Long reportId);
}