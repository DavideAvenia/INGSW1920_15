package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.Entity.Strutture;

import java.sql.*;
import java.util.Map;

public class GetStrutturaByNomePosizione implements RequestHandler<Map<String,String>, Strutture> {

    private final String URLDB = "";
    @Override
    public Strutture handleRequest(Map<String, String> bodyRequest, Context context) {
        Strutture struttura = null;

        String nomeStruttura = bodyRequest.get("nomeStruttura");
        String lat = bodyRequest.get("latitudine");
        String lon = bodyRequest.get("longitudine");

        DatabaseConnection databaseConnection = new DatabaseConnection();
        try{

            ResultSet resultSet = databaseConnection.eseguiQuery("select * from Strutture where nome=\""+nomeStruttura+"\" AND latitudine=\""+lat+"\" AND longitudine=\""+lon+"\";");
            while(resultSet.next()){
                String nome = resultSet.getString("nome");
                String città = resultSet.getString("città");
                float valutazioneMedia = resultSet.getFloat("valutazioneMedia");
                String maxPrezzo = resultSet.getString("maxPrezzo");
                String orarioApertura = resultSet.getString("orarioApertura");
                String categoria = resultSet.getString("categoria");
                String latitudine = resultSet.getString("latitudine");
                String longitudine = resultSet.getString("longitudine");
                String descrizione = resultSet.getString("descrizione");

                struttura = new Strutture(nome,città,valutazioneMedia,maxPrezzo,orarioApertura,categoria,latitudine,longitudine,descrizione);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return struttura;
    }
}
