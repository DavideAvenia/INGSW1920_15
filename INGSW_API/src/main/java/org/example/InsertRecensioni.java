package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class InsertRecensioni implements RequestHandler<Map<String,String>,Boolean> {
    @Override
    public Boolean handleRequest(Map<String, String> requestBody, Context context) {

        String nomeUtente = requestBody.get("nomeUtente");
        String nomeStruttura = requestBody.get("nomeStruttura");
        String latitudine = requestBody.get("latitudine");
        String longitudine = requestBody.get("longitudine");
        String testoRecensione = requestBody.get("testoRecensione");
        float valutazione = Float.parseFloat(requestBody.get("valutazione"));
        String urlImmagine = requestBody.get("urlImmagine");

        DatabaseConnection databaseConnection = new DatabaseConnection();

        //1 alla fine della query signifca "In attesa" sarebbe il parametro Pending
        //vuol dire che è settato a True quindi rimane in attesa di conferma

        databaseConnection.updateEntries("insert into Recensioni values(\"" + nomeUtente +"\",\"" + nomeStruttura +"\",\""
                + latitudine + "\",\"" + longitudine + "\",\"" + testoRecensione + "\",\"" + urlImmagine + "\",\"" + valutazione + "\",\"" + "1" + "\");");

        return true;
    }
}
