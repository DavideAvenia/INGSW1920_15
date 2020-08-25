package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class DeleteStatisticheUtente implements RequestHandler<Map<String, String>, Boolean> {
    @Override
    public Boolean handleRequest(Map<String, String> bodyRequest, Context context) {

        String username = bodyRequest.get("userId");

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.updateEntries("delete from StatisticheUtenti where userID = \""+username+"\";");
        return true;
    }
}
