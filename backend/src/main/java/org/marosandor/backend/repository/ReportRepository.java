package org.marosandor.backend.repository;

import org.marosandor.backend.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUserId(Long userId);
    List<Report> findByAssignedWorkerIdOrderByCreatedAtDesc(Long workerId);
    List<Report> findByStatusIdOrderByCreatedAtDesc(Long statusId);
}
