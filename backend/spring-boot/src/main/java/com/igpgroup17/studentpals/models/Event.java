package com.igpgroup17.studentpals.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "events")
public class Event {

    @DynamoDBHashKey(attributeName = "eventId")
    @DynamoDBAutoGeneratedKey
    private String eventID;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "description")
    private String description;

    @DynamoDBAttribute(attributeName = "organiserId")
    private String organiserID;

    @DynamoDBAttribute(attributeName = "goingUserIds")
    private List<String> goingUsersIDs;

    @DynamoDBAttribute(attributeName = "interestedUserIds")
    private List<String> interestedUsersIDs;

    @DynamoDBAttribute(attributeName = "likes")
    private int likes;

    @DynamoDBAttribute(attributeName = "state")
    private String state;

    @DynamoDBAttribute(attributeName = "scheduledTime")
    private String scheduledTime;

    @DynamoDBAttribute(attributeName = "isVirtual")
    private Boolean isVirtual;

    @DynamoDBAttribute(attributeName = "isAlcoholFree")
    private Boolean isAlcoholFree;

    @DynamoDBAttribute(attributeName = "isInPerson")
    private Boolean isInPerson;

}