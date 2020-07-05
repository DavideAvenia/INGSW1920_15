package com.example.appmobile.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.entity.Recensioni;
import com.example.appmobile.entity.Strutture;
import com.google.android.gms.maps.model.LatLng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AWSMySQLRds implements StruttureDao, RecensioniDao {

    private Context context;
    Connection conn = null;


    private final String URL = "";
    private final String PORT = "3306";
    private final String USERNAME = "";
    private final String PASSWORD = "";

    public AWSMySQLRds(Context context){
        this.context = context;
    }

    @Override
    public List<Strutture> getStruttureByFiltri(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String rangePrezzo) {
        /*System.out.println("stiamo recuperando le strutture");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://i");
            Statement stmt = conn.createStatement();

            int i = stmt.executeUpdate("insert into test value('Davide','Android');");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        List<Strutture> listaStrutture = new ArrayList<Strutture>();

        listaStrutture.add(new Strutture("Stadio San Paolo","Napoli",4.0f,"50-300€","20:00-23:00","Intrattenimento",40.827967f,14.193008f));
        listaStrutture.add(new Strutture("Anfiteatro Flavio","Pozzuoli",3.9f,"5-20€","9:00-18:00","Museo",40.82336f,14.12434f));
        listaStrutture.add(new Strutture("Pompei","Pompei",4.9f,"10-50€","9:00-21:00","Museo",40.749183654785156f,14.500738143920898f));
        listaStrutture.add(new Strutture("Piazza del Plebiscito","Napoli",3.5f,"Gratuito","8:00-18:00","Cultura",40.85299f,14.24789f));
        listaStrutture.add(new Strutture("Teatro San Carlo","Napoli",5.0f,"50-120€","18:00-23:00","Intrattenimento",40.85299f,14.24789f));
        listaStrutture.add(new Strutture("Castel dell'ovo","Napoli",4.9f,"Gratuito","8:00-21:00","Culto",40.827222f,14.248611f));

        return listaStrutture;
    }

    @Override
    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, LatLng posizione) {
        return null;
    }
}
