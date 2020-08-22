package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Connection;
import java.util.Map;

public class IncrementaLoginCounter implements RequestHandler<Map<String,String>,Boolean> {

    @Override
    public Boolean handleRequest(Map<String, String> requestBody, Context context) {

        String username = requestBody.get("username");

        DatabaseConnection databaseConnection = new DatabaseConnection();

        databaseConnection.updateEntries("update StatisticheUtenti set loginCounter = loginCounter + 1 where userID = \""+username+"\";");

        return true;
    }
}
