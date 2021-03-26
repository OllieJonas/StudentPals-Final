package com.igpgroup17.studentpals.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.igpgroup17.studentpals.dao.OrganiserCrudDao;
import com.igpgroup17.studentpals.models.Organiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrganiserServiceDaoImpl implements OrganiserCrudDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public OrganiserServiceDaoImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Organiser createOrganiser(Organiser organiser) {
        dynamoDBMapper.save(organiser);
        return organiser;
    }

    @Override
    public Organiser readOrganiser(String organiserId) {
        return dynamoDBMapper.load(Organiser.class, organiserId);
    }

    @Override
    public Organiser updateOrganiser(Organiser organiser) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("organiserId", new ExpectedAttributeValue(new AttributeValue().withS(organiser.getOrganiserId())));
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(organiser, saveExpression);
        return organiser;
    }

    @Override
    public void deleteOrganiser(String organiserId) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("organiserId", new ExpectedAttributeValue(new AttributeValue().withS(organiserId)));
        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression().withExpected(expectedAttributeValueMap);
        Organiser organiser = Organiser.builder()
                .organiserId(organiserId)
                .build();
        dynamoDBMapper.delete(organiser, deleteExpression);
    }
}
