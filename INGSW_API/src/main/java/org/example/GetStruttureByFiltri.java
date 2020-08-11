package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.Entity.Strutture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetStruttureByFiltri implements RequestHandler<Map<String,String>, List<Strutture>> {

    private final String URLDB = "jdbc:mysql://ingswdatabase.czrrnx3ltups.eu-west-1.rds.amazonaws.com:3306/ingsw?user=admin&password=6x1li30di3IoU2Mgwaoy";

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

        if(filtroNome.length() != 0){
            query = query+"nome=\""+filtroNome+"\" AND ";
        }

        if(!filtroCittà.equals("Qualunque")){
            query = query+"città=\""+filtroCittà+"\" AND ";
        }

        if(!filtroMaxPrezzo.equals("Qualunque")){
            query = query+"maxPrezzo<=\""+filtroMaxPrezzo+"\" AND ";
        }

        if(!filtroOrarioApertura.equals("Qualunque")){
            query = query+"orarioApertura=\""+filtroOrarioApertura+"\" AND ";
        }

        if(!filtroCategoria.equals("Qualunque")){
            query = query +"categoria=\""+filtroCategoria+"\" AND ";
        }
        query = query+"valutazioneMedia>=\""+filtroValutazione+"\";";


        /*Recupero delle struttre*/
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try{

            ResultSet resultSet = databaseConnection.eseguiQuery(query);
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

                Strutture struttura = new Strutture(nome,città,valutazioneMedia,maxPrezzo,orarioApertura,categoria,latitudine,longitudine,descrizione);
                listaStrutture.add(struttura);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaStrutture;
    }
}
