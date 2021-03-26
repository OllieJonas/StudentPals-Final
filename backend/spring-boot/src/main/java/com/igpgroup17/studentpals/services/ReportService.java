package com.igpgroup17.studentpals.services;


import com.igpgroup17.studentpals.models.Reports;

public interface ReportService {

    Reports createReport(Reports student);

    Reports readReport(String id);

    Reports updateReport(Reports student);

    String deleteReport(String id);
}
