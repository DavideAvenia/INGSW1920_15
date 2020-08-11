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
                String nome = resultSet.getString(1);
                String città = resultSet.getString(2);
                float valutazioneMedia = resultSet.getFloat(3);
                String maxPrezzo = resultSet.getString(4);
                String orarioApertura = resultSet.getString(5);
                String categoria = resultSet.getString(6);
                float latitudine = resultSet.getFloat(7);
                float longitudine = resultSet.getFloat(8);
                String descrizione = resultSet.getString(9);

                struttura = new Strutture(nome,città,valutazioneMedia,maxPrezzo,orarioApertura,categoria,latitudine,longitudine,descrizione);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return struttura;
    }
}
