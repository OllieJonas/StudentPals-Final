package com.igpgroup17.studentpals.controllers;

import com.igpgroup17.studentpals.models.Reports;
import com.igpgroup17.studentpals.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/v1/reports/{id}")
    public ResponseEntity<?> getReportById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(reportService.readReport(id));
    }

    @PostMapping("/v1/reports")
    public ResponseEntity<?> createReport(@RequestBody Reports reports) {
        return ResponseEntity.ok(reportService.createReport(reports));
    }

    @PutMapping("/v1/reports")
    public ResponseEntity<?> updateReport(@RequestBody Reports reports) {
        return ResponseEntity.ok(reportService.updateReport(reports));
    }

    @DeleteMapping("/v1/reports/{id}")
    public ResponseEntity<?> deleteReportById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(reportService.deleteReport(id));
    }
}
