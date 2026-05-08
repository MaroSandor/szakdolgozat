package org.marosandor.backend.repository;

import org.marosandor.backend.entity.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, Long> {
    Optional<ReportStatus> findByEmailAndUsername(String email, String username);
    List<ReportStatus> findByUsername(String username);
}
