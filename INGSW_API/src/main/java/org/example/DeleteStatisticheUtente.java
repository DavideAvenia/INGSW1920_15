package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.SQLException;
import java.util.Map;

public class DeleteStatisticheUtente implements RequestHandler<Map<String, String>, Boolean> {
    @Override
    public Boolean handleRequest(Map<String, String> bodyRequest, Context context) {

        String username = bodyRequest.get("userId");

        DatabaseConnection databaseConnection = new DatabaseConnection();
        try{
            databaseConnection.updateEntries("delete from StatisticheUtenti where userID = \""+username+"\";");
        }catch (SQLException e){
            return false;
        }
        return true;
    }
}
