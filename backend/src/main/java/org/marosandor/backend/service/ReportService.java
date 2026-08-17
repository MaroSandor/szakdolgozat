package org.marosandor.backend.service;

import lombok.*;
import org.marosandor.backend.config.JwtUtil;
import org.marosandor.backend.dto.report.ReportRequest;
import org.marosandor.backend.dto.report.ReportResponse;
import org.marosandor.backend.entity.Report;
import org.marosandor.backend.entity.ReportStatus;
import org.marosandor.backend.entity.ReportStatusHistory;
import org.marosandor.backend.entity.User;
import org.marosandor.backend.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ReportStatusRepository reportStatusRepository;
    private final ReportStatusHistoryRepository reportStatusHistoryRepository;

    public ReportResponse createReport(ReportRequest reportRequest, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található!"));

        var defaultStatus = reportStatusRepository.findByName("PENDING")
                .orElseThrow(() -> new RuntimeException("Alapértelmezett státusz nem található!"));

        Report report = Report.builder()
                .user(reportRequest.isAnonymous() ? null : user)
                .status(defaultStatus)
                .title(reportRequest.getTitle())
                .description(reportRequest.getDescription())
                .category(reportRequest.getCategory())
                .latitude(reportRequest.getLatitude())
                .longitude(reportRequest.getLongitude())
                .isAnonymous(reportRequest.isAnonymous())
                .build();

        reportRepository.save(report);

        ReportStatusHistory reportStatusHistory = ReportStatusHistory.builder()
                .report(report)
                .status(defaultStatus)
                .changedBy(user)
                .build();

        reportStatusHistoryRepository.save(reportStatusHistory);

        return toResponse(report);
    }

    public List<ReportResponse> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ReportResponse getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bejelentés nem található!"));
        return toResponse(report);
    }

    public List<ReportResponse> getMyReports(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található!"));
        return reportRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ReportResponse toResponse(Report report) {
        return new ReportResponse(
                report.getId(),
                report.getTitle(),
                report.getDescription(),
                report.getCategory(),
                report.getLatitude(),
                report.getLongitude(),
                report.getStatus().getLabel(),
                report.isAnonymous(),
                report.isAnonymous() ? null : report.getUser().getUsername(),
                report.getCreatedAt()
        );
    }
}
