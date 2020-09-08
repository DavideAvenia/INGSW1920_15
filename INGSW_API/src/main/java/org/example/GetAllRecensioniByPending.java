package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.Entity.Recensioni;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllRecensioniByPending implements RequestHandler<Map<String, String>, List<Recensioni>> {
    @Override
    public List<Recensioni> handleRequest(Map<String, String> requestBody, Context context) {
        List<Recensioni> listaRecensioni = new ArrayList<Recensioni>();
        DatabaseConnection DB = new DatabaseConnection();

        try {
            ResultSet res = DB.eseguiQuery("select * from Recensioni where Pending = 1");
            while (res.next()){
                String usernameUtente = res.getString("usernameUtente");
                String nomeStruttura = res.getString("nomeStruttura");
                String latitudine = res.getString("latitudine");
                String longitudine = res.getString("longitudine");
                String testoRecensione = res.getString("testoRecensione");
                String urlImmagine = res.getString("urlFoto");
                float valutazione = res.getFloat("valutazione");

                Recensioni r = new Recensioni(testoRecensione, urlImmagine, valutazione, usernameUtente, nomeStruttura, latitudine, longitudine);
                listaRecensioni.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listaRecensioni;
    }
}
