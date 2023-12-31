package com.BazyDanych2Projekt.BazyDanychProjekt.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @GetMapping("/list")
    public List<Report> getReportList() {
        return reportRepository.getReportList();
    }

    @GetMapping("/seller/{id}")
    public List<Report> getReportListBySellerId(@PathVariable("id") int id) {
        return reportRepository.getReportListBySellerId(id);
    }

    @GetMapping("/{id}")
    public Report getReportById(@PathVariable("id") int id) {
        return reportRepository.getReportById(id);
    }

    @PostMapping("/add")
    public String addReport(@RequestBody List<Report> reports) {
        return reportRepository.saveReports(reports);
    }

    @PatchMapping("/{id}")
    public String updateReport(@PathVariable("id") int id, @RequestBody Report updatedReport) {
        Report report = reportRepository.getReportById(id);

        if (report != null) {
            if (updatedReport.getSeller_id() > 0) {
                report.setSeller_id(updatedReport.getSeller_id());
            }

            reportRepository.updateReport(report);

            return "Success!";
        } else {
            return "Error";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable("id") int id) {
        return reportRepository.deleteReport(id);
    }
}
