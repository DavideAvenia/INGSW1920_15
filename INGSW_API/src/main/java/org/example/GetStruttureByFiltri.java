package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.Entity.Strutture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetStruttureByFiltri implements RequestHandler<Map<String,String>, List<Strutture>> {

    @Override
    public List<Strutture> handleRequest(Map<String, String> requestBody, Context context) {
        List<Strutture> listaStrutture = new ArrayList<Strutture>();
        String query = "select * from Strutture where ";

        /*Recupero dei filtri dalla richiesta*/
        String filtroNome = requestBody.get("nome");
        String filtroCittà = requestBody.get("città");
        float filtroValutazione = Float.parseFloat(requestBody.get("valutazioneMedia"));
        String filtroMaxPrezzo = requestBody.get("maxPrezzo");
        String filtroOrarioApertura = requestBody.get("orarioApertura");
        String filtroCategoria = requestBody.get("categoria");

        if(filtroNome.length() != 0 && !filtroNome.contains("\"")){
            query = query+"nome=\""+filtroNome+"\" AND ";
        }

        if(!filtroCittà.equals("Qualunque")){
            query = query+"città=\""+filtroCittà+"\" AND ";
        }

        if(!filtroMaxPrezzo.equals("Qualunque")){
            query = query+"maxPrezzo<="+filtroMaxPrezzo+" AND ";
        }

        if(!filtroOrarioApertura.equals("Qualunque")){
            query = query+"orarioApertura=\""+filtroOrarioApertura+"\" AND ";
        }

        if(!filtroCategoria.equals("Qualunque")){
            query = query +"categoria=\""+filtroCategoria+"\" AND ";
        }
        query = query+"valutazioneMedia>="+filtroValutazione+";";

        /*Recupero delle struttre*/
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try{

            ResultSet resultSet = databaseConnection.eseguiQuery(query);
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

                Strutture struttura = new Strutture(nome,città,valutazioneMedia,maxPrezzo,orarioApertura,categoria,latitudine,longitudine,descrizione);
                listaStrutture.add(struttura);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaStrutture;
    }
}
