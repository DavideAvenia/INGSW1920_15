package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.Entity.Recensioni;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetRecensioniByNomeStrutturaPosizione implements RequestHandler<Map<String,String>, List<Recensioni>> {

    @Override
    public List<Recensioni> handleRequest(Map<String, String> requestBody, Context context) {
        List<Recensioni> listaRecensioni = new ArrayList<Recensioni>();

        /*Rercupero body della richiesta*/
        String nomeStruttura = requestBody.get("nomeStruttura");
        String latitudine = requestBody.get("latitudine");
        String longitudine = requestBody.get("longitudine");

        /*Connessione database*/
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try{
            ResultSet res = databaseConnection.eseguiQuery("select * from Recensioni where nomeStruttura=\""+nomeStruttura+"\" AND latitudine=\""+latitudine+"\" AND longitudine=\""+longitudine+"\";");

            while(res.next()){
                String usernameUtente = res.getString(1);
                String testoRecensione = res.getString(5);
                String urlFoto = res.getString(6);
                Float valutazione = Float.parseFloat(res.getString(7));

                Recensioni recensione = new Recensioni(testoRecensione,urlFoto,valutazione,usernameUtente,nomeStruttura,Float.parseFloat(latitudine),Float.parseFloat(longitudine));
                listaRecensioni.add(recensione);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaRecensioni;
    }
}
