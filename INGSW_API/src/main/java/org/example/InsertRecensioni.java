package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

public class InsertRecensioni implements RequestHandler<Map<String,String>,Boolean> {
    @Override
    public Boolean handleRequest(Map<String, String> requestBody, Context context) {

        String userId = requestBody.get("userId");
        String nomeStruttura = requestBody.get("nomeStruttura");
        String latitudine = requestBody.get("latitudine");
        String longitudine = requestBody.get("longitudine");
        String testoRecensione = requestBody.get("testoRecensione");
        float valutazione = Float.parseFloat(requestBody.get("valutazione"));
        String urlImmagine = requestBody.get("urlImmagine");

        DatabaseConnection databaseConnection = new DatabaseConnection();

        //1 alla fine della query signifca "In attesa" sarebbe il parametro Pending
        //vuol dire che è settato a True quindi rimane in attesa di conferma

        try {
            databaseConnection.updateEntries("insert into Recensioni values(\"" + userId + "\",\"" + nomeStruttura + "\",\""
                    + latitudine + "\",\"" + longitudine + "\",\"" + testoRecensione + "\",\"" + urlImmagine + "\",\"" + valutazione + "\",\"" + "1" + "\");");
        }catch(SQLIntegrityConstraintViolationException e){
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
