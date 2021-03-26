package com.igpgroup17.studentpals.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.igpgroup17.studentpals.dao.ReportCrudDao;
import com.igpgroup17.studentpals.models.Reports;
import com.igpgroup17.studentpals.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReportServiceDaoImpl implements ReportCrudDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired

    public ReportServiceDaoImpl(DynamoDBMapper dynamoDBMapper) { this.dynamoDBMapper = dynamoDBMapper; }

    @Override
    public Reports createReport(Reports reports) {
        dynamoDBMapper.save(reports);
        return reports;
    }

    @Override
    public Reports readReport(String reportId) {
        return dynamoDBMapper.load(Reports.class, reportId);
    }

    @Override
    public Reports updateReport(Reports report) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("reportId", new ExpectedAttributeValue(new AttributeValue().withS(report.getReportId())));
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(report, saveExpression);
        return report;
    }

    @Override
    public void deleteReport(String reportId) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("reportId", new ExpectedAttributeValue(new AttributeValue().withS(reportId)));
        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression().withExpected(expectedAttributeValueMap);
        Reports report = Reports.builder()
                .reportId(reportId)
                .build();
        dynamoDBMapper.delete(report, deleteExpression);
    }

}

