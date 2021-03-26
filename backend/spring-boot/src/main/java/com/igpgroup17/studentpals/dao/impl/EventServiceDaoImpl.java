package com.igpgroup17.studentpals.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.igpgroup17.studentpals.dao.EventCrudDao;
import com.igpgroup17.studentpals.models.Event;
import com.igpgroup17.studentpals.models.Review;
import com.igpgroup17.studentpals.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventServiceDaoImpl implements EventCrudDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public EventServiceDaoImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Event createEvent(Event event) {
        dynamoDBMapper.save(event);
        return event;
    }

    @Override
    public Event readEvent(String eventId) {
        return dynamoDBMapper.load(Event.class, eventId);
    }

    @Override
    public Event updateEvent(Event event) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("eventId", new ExpectedAttributeValue(new AttributeValue().withS(event.getEventID())));
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(event, saveExpression);
        return event;
    }

    @Override
    public void deleteEvent(String eventId) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("eventId", new ExpectedAttributeValue(new AttributeValue().withS(eventId)));
        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression().withExpected(expectedAttributeValueMap);
        Event event = Event.builder()
                .eventID(eventId)
                .build();
        dynamoDBMapper.delete(event, deleteExpression);
    }

    @Override
    public List<Event> getAllEventsFor(String organiserId) {
        Map<String, AttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put(":organiserId", new AttributeValue().withS(organiserId));
        DynamoDBScanExpression expression = new DynamoDBScanExpression().withFilterExpression("organiserId = :organiserId").withExpressionAttributeValues(expectedAttributeValueMap);

        return dynamoDBMapper.parallelScan(Event.class, expression, 5);
    }
}
