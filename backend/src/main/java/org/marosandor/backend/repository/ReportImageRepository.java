package org.marosandor.backend.repository;

import org.marosandor.backend.entity.ReportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportImageRepository extends JpaRepository<ReportImageRepository, Long> {
    List<ReportImage> findByReportId(Long reportId);
}
