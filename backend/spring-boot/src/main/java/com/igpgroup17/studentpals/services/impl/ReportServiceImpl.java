package com.igpgroup17.studentpals.services.impl;

import com.igpgroup17.studentpals.dao.ReportCrudDao;
import com.igpgroup17.studentpals.models.Reports;
import com.igpgroup17.studentpals.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportCrudDao reportCrudDao;

    @Autowired
    public ReportServiceImpl(ReportCrudDao reportCrudDao) {
        this.reportCrudDao = reportCrudDao;
    }

    @Override
    public Reports createReport(Reports reports) {
        return reportCrudDao.createReport(reports);
    }

    @Override
    public Reports readReport(String id) {
        return reportCrudDao.readReport(id);
    }

    @Override
    public Reports updateReport(Reports reports) {
        return reportCrudDao.updateReport(reports);
    }

    @Override
    public String deleteReport(String id) {
        reportCrudDao.deleteReport(id);
        return "Deleted student";
    }
}
