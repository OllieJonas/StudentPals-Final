package com.igpgroup17.studentpals.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.igpgroup17.studentpals.dao.ReviewCrudDao;
import com.igpgroup17.studentpals.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReviewServiceDaoImpl implements ReviewCrudDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ReviewServiceDaoImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Review createReview(Review review) {
        dynamoDBMapper.save(review);
        return review;
    }

    @Override
    public Review readReview(String reviewId) {
        return dynamoDBMapper.load(Review.class, reviewId);
    }

    @Override
    public Review updateReview(Review review) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("reviewId", new ExpectedAttributeValue(new AttributeValue().withS(review.getReviewId())));
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(review, saveExpression);
        return review;
    }

    @Override
    public void deleteReview(String reviewId) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("reviewId", new ExpectedAttributeValue(new AttributeValue().withS(reviewId)));
        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression().withExpected(expectedAttributeValueMap);
        Review review = Review.builder()
                .reviewId(reviewId)
                .build();
        dynamoDBMapper.delete(review, deleteExpression);
    }

    @Override
    public List<Review> getReviewsFor(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));
        DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
                .withFilterExpression("organiserId = :id")
                .withExpressionAttributeValues(eav);
        return dynamoDBMapper.parallelScan(Review.class, queryExpression, 5);
    }
}
