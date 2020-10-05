package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.sql.SQLException;
import java.util.Map;

public class IncrementaNumeroVisitatori implements RequestHandler<Map<String,String>,Boolean> {

    @Override
    public Boolean handleRequest(Map<String, String> bodyRequest, Context context) {

        String nomeStruttura = bodyRequest.get("nomeStruttura");
        String latitudine = bodyRequest.get("latitudine");
        String longitudine = bodyRequest.get("longitudine");

        DatabaseConnection databaseConnection = new DatabaseConnection();

    try{
        databaseConnection.updateEntries("update StatisticheStrutture SET numeroVisitatori = numeroVisitatori + 1 where nomeStruttura=\""+nomeStruttura+"\" AND latitudine=\""+latitudine+"\" AND longitudine=\""+longitudine+"\";");
    }catch (SQLException e){
        return false;
    }
        return true;
    }
}
