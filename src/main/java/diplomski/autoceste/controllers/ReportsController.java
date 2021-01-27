package diplomski.autoceste.controllers;

import diplomski.autoceste.models.Report;
import diplomski.autoceste.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ReportsController {

    private ReportService reportService;

    @Autowired
    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Report>> getReports() {
        return ResponseEntity.of(
                Optional.of(this.reportService.getReports()));
    }

}
