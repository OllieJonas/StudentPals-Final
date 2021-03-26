package com.igpgroup17.studentpals.dao;

import com.igpgroup17.studentpals.models.Reports;

public interface ReportCrudDao {
    Reports createReport(Reports user);
    Reports readReport(String reportId);
    Reports updateReport(Reports report);
    void deleteReport(String reportId);
}
