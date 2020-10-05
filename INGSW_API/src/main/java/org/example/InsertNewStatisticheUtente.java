package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.SQLException;
import java.util.Map;

public class InsertNewStatisticheUtente implements RequestHandler<Map<String,String>,Boolean> {
    @Override
    public Boolean handleRequest(Map<String, String> requestBody, Context context) {

        String username = requestBody.get("username").toLowerCase();

        DatabaseConnection databaseConnection = new DatabaseConnection();

        try{
            databaseConnection.updateEntries("insert into StatisticheUtenti values(\""+username+"\",\"0\",\"0\",\"0\",\"0\")");
        }catch (SQLException e){
            return false;
        }
        return true;
    }
}
