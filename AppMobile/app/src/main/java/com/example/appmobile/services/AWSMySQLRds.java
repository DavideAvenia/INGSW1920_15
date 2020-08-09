package com.example.appmobile.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.entity.Recensioni;
import com.example.appmobile.entity.StatisticheStrutture;
import com.example.appmobile.entity.Strutture;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AWSMySQLRds implements StruttureDao, RecensioniDao {

    private Context context;

    public AWSMySQLRds(Context context){
        this.context = context;
    }

    @Override
    public List<Strutture> getStruttureByFiltri(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String maxPrezzo) {
        final List<Strutture> listaStrutture = new ArrayList<Strutture>();

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();

        try {
            if(nome.length()!=0){
                jsonObject.put("nome",nome);
            }else{
                jsonObject.put("nome","");
            }
            jsonObject.put("città",città);
            jsonObject.put("valutazioneMedia",valutazioneMedia);
            jsonObject.put("orarioApertura",orarioApertura);
            jsonObject.put("categoria",categoria);
            jsonObject.put("maxPrezzo",maxPrezzo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON,jsonObject.toString());
        Request request = new Request.Builder().url(URLAPIGETSTRUTTUREBYFILTRI).post(requestBody).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.isSuccessful()) {
            try {
                JSONArray jsonArray = new JSONArray(response.body().string());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject struttura = jsonArray.getJSONObject(i);

                    String nomes = struttura.get("nome").toString();
                    String cittàs = struttura.get("città").toString();
                    float valutaziones = Float.parseFloat(struttura.get("valutazioneMedia").toString());
                    String prezzos = struttura.get("maxPrezzo").toString();
                    String orarios = struttura.get("orarioApertura").toString();
                    String categorias = struttura.get("categoria").toString();
                    float lats = Float.parseFloat(struttura.get("latitudine").toString());
                    float lans = Float.parseFloat(struttura.get("longitudine").toString());
                    String descrs = struttura.get("descrizione").toString();

                    Strutture s = new Strutture(nomes, cittàs, valutaziones, prezzos, orarios, categorias, lats, lans, descrs);
                    listaStrutture.add(s);
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this.context,"Errore recupero dati!\n",Toast.LENGTH_LONG).show();
        }
        return listaStrutture;
    }

    @Override
    public Strutture getStrutturaByNomePosizione(String nome, LatLng posizione) {

        /*Scrivere il codice che si connette al database*/


        /*IL CODICE QUI SOTTO E' SOLO PER FARE TEST*/

        return null;
    }

    @Override
    public void incrementaNumeroVisitatori(String nome, LatLng posizione) {

    }

    @Override
    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, LatLng posizione) {
        final List<Recensioni> listaRecensioni = new ArrayList<Recensioni>();

        /*Costruzione body richiesta http api*/
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();

        double lat = posizione.latitude;
        double lon = posizione.longitude;
        try{
            jsonObject.put("nomeStruttura",nomeStruttura);
            jsonObject.put("latitudine",lat);
            jsonObject.put("longitudine",lon);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*Invio richiesta http*/
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON,jsonObject.toString());
        Request request = new Request.Builder().url(URLAPIGETRECENSIONIBYNOMESTRUTTURAPOSIZIONE).post(requestBody).build();

        Response response = null;
        try{
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response.isSuccessful()){
            /*Parsing recensioni*/

            Recensioni recensione = null;
            listaRecensioni.add(recensione);
        }



        /*IL CODICE QUI SOTTO E' SOLO PER FARE TEST*/


        return listaRecensioni;
    }




}
