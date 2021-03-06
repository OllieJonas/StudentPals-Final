package com.igpgroup17.studentpals.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "organiser-table")
public class Organiser {

    @DynamoDBHashKey(attributeName = "organiserId")
    @DynamoDBAutoGeneratedKey
    private String organiserId;

    @DynamoDBAttribute(attributeName = "profileId")
    private String profileId;

    @DynamoDBAttribute(attributeName = "organisationName")
    private String organisationName;

    @DynamoDBAttribute(attributeName = "organisationDesc")
    private String organisationDesc;

    @DynamoDBAttribute(attributeName = "uniEmail")
    private String uniEmail;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

    @DynamoDBAttribute(attributeName = "universityName")
    private String universityName;

}