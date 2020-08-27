package com.example.appmobile.services;

import android.content.Context;
import android.widget.Toast;

import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.entity.Recensioni;
import com.example.appmobile.entity.Strutture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
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

        /*Creazione body richiesta*/
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
        /*Invio richiesta http*/
        Request request = createRequest(jsonObject,URLAPIGETSTRUTTUREBYFILTRI);

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
                    String lats = struttura.get("latitudine").toString();
                    String lans = struttura.get("longitudine").toString();
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
    public Strutture getStrutturaByNomePosizione(String nome, String latitudine, String longitudine) {
        Strutture strutt = null;
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();

        /*Creazione body richiesta*/
        try{
            jsonObject.put("nomeStruttura",nome);
            jsonObject.put("latitudine",latitudine);
            jsonObject.put("longitudine",longitudine);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*Invio richiesta http*/
        Request request = createRequest(jsonObject,URLAPIGETSTRUTTURABYNOMEPOSIZIONE);

        Response response = null;
        try{
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(response.isSuccessful()){
            try{
                /*Parsing struttura*/
                JSONObject struttura = new JSONObject(response.body().string());

                String nomes = struttura.get("nome").toString();
                String cittàs = struttura.get("città").toString();
                float valutaziones = Float.parseFloat(struttura.get("valutazioneMedia").toString());
                String prezzos = struttura.get("maxPrezzo").toString();
                String orarios = struttura.get("orarioApertura").toString();
                String categorias = struttura.get("categoria").toString();
                String lats = struttura.get("latitudine").toString();
                String lans = struttura.get("longitudine").toString();
                String descrs = struttura.get("descrizione").toString();

                strutt = new Strutture(nomes, cittàs, valutaziones, prezzos, orarios, categorias, lats, lans, descrs);

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }
        return strutt;
    }

    @Override
    public void incrementaNumeroVisitatori(String nome, String latitudine, String longitudine) {

        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();

        /*Creazione body richiesta*/
        try{
            jsonObject.put("nomeStruttura",nome);
            jsonObject.put("latitudine",latitudine);
            jsonObject.put("longitudine",longitudine);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*Invio richiesta http*/
        Request request = createRequest(jsonObject,URLAPIINCREMENTANUMEROVISITATORI);

        Response response = null;
        try{
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, String latitudine, String longitudine) {
        final List<Recensioni> listaRecensioni = new ArrayList<Recensioni>();

        /*Costruzione body richiesta http api*/
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();

        /*Creazione body richiesta*/
        Double lat = Double.parseDouble(latitudine);
        Double lon = Double.parseDouble(longitudine);
        try{
            jsonObject.put("nomeStruttura",nomeStruttura);
            jsonObject.put("latitudine",latitudine);
            jsonObject.put("longitudine",longitudine);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*Invio richiesta http*/
        Request request = createRequest(jsonObject,URLAPIGETRECENSIONIBYNOMESTRUTTURAPOSIZIONE);

        Response response = null;
        try{
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(response.isSuccessful()){
            /*Parsing recensioni*/
            try{
                JSONArray jsonArray = new JSONArray(response.body().string());

                for (int i = 0; i < jsonArray.length(); i++) {
                   JSONObject recensioneJSON = jsonArray.getJSONObject(i);

                    String testoRecensione = recensioneJSON.get("testoRecensione").toString();
                    String urlImmagine = recensioneJSON.get("urlImmagine").toString();
                    Float valutazione = Float.parseFloat(recensioneJSON.get("valutazione").toString());
                    String usernameUtente = recensioneJSON.get("userNameUtente").toString();

                    Recensioni recensione = new Recensioni(testoRecensione,urlImmagine,valutazione,usernameUtente,nomeStruttura,lat,lon);
                    listaRecensioni.add(recensione);
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

        }
        return listaRecensioni;
    }

    @Override
    public boolean insertRecensioni(String nomeUtente, String nomeStruttura, String testoRecensione, float valutazione, String urlImmagine) {
        /*Costruzione body richiesta http api*/
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();

        return false;
    }

    private Request createRequest(JSONObject jsonObject, final String API){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON,jsonObject.toString());
        return new Request.Builder().url(API).post(requestBody).build();
    }



}
