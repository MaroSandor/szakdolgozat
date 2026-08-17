package org.marosandor.backend.controller;

import jakarta.validation.Valid;
import lombok.*;
import org.marosandor.backend.dto.report.*;
import org.marosandor.backend.service.ReportService;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@Valid @RequestBody ReportRequest reportRequest, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reportService.createReport(reportRequest, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReportResponse>> getMyReports(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(reportService.getMyReports(userDetails.getUsername()));
    }
}
