package com.example

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.*

object DynamoDBManager {

    val awsCreds = BasicAWSCredentials("","")

    val amazonDynamoDb = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.US_EAST_2)
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .build()

    fun createTable(tableName: String): CreateTableResult  {
        val createTableRequest = CreateTableRequest()
                .withAttributeDefinitions(AttributeDefinition("id", ScalarAttributeType.N))
                .withKeySchema(KeySchemaElement("id", KeyType.HASH))
                .withProvisionedThroughput(ProvisionedThroughput(10L, 10L))
                .withTableName(tableName)

        return amazonDynamoDb.createTable(createTableRequest)
    }

    fun deleteTable(tableName: String): DeleteTableResult {
        return amazonDynamoDb.deleteTable(tableName)
    }
}