package com.example

import com.amazonaws.services.dynamodbv2.model.ResourceInUseException
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException

fun main(args: Array<String>) {

    val tableName = "users"

    try {
        val createTableResult = DynamoDBManager.createTable(tableName)
        println(createTableResult)
    } catch (e: Exception) {
        when (e) {
            is ResourceInUseException -> println("Table already exists")
            else -> e.printStackTrace()
        }
    }

    try {
        val deleteTableResult = DynamoDBManager.deleteTable(tableName)
        println(deleteTableResult)
    } catch (e: Exception) {
        when (e) {
            is ResourceNotFoundException -> println("Table already exists")
            else -> e.printStackTrace()
        }
    }
}