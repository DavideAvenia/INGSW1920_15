package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.SQLException;
import java.util.Map;

public class DisapprovaRecensione implements RequestHandler<Map<String,String>,Boolean> {
    @Override
    public Boolean handleRequest(Map<String, String> requestyBody, Context context) {
            DatabaseConnection db = new DatabaseConnection();
            String usernameUtente = requestyBody.get("usernameUtente");
            String nomeStruttura = requestyBody.get("nomeStruttura");
            String latitudine = requestyBody.get("latitudine");
            String longitudine = requestyBody.get("longitudine");

            try{
                db.updateEntries("DELETE FROM Recensioni " +
                        "Where usernameUtente =\""+ usernameUtente +"\" AND nomeStruttura = \"" + nomeStruttura + "\" " +
                        "AND latitudine = \"" + latitudine + "\" AND longitudine = \"" + longitudine + "\";");
            }catch (SQLException e){
                return false;
            }

            return true;
    }
}