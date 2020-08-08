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

        initDatabase();
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
        List<Recensioni> listaRecensioni = new ArrayList<Recensioni>();

        /*Scrivere il codice che si connette al database*/


        /*IL CODICE QUI SOTTO E' SOLO PER FARE TEST*/
        for(Recensioni r: listaRecensioniDatabase){
            if(r.getUserNameUtente().equals(nomeStruttura) && r.getLatitudine() == posizione.latitude && r.getLongitudine() == posizione.longitude){
                listaRecensioni.add(r);
            }
        }

        return listaRecensioni;
    }


    /*
    *
    *   LA PARTE SOTTOSTANTE E' USATA SEMPLICEMENTE PER SOPPERIRE ALLA MANCAZA DELLE REALI IMPLEMENTAZIONI CHE DOVREBBERO FORNIRE TALI DATI.
    *   ALLA FINE DELLO SVILUPPO TALE PARTE VERRA' CANCELLATA.
    *
    * */

    protected static List<Recensioni> listaRecensioniDatabase = new ArrayList<Recensioni>();

    private void initDatabase(){
        listaRecensioniDatabase.add(new Recensioni("Lo stadio San Paolo è fighissimo!!","https://www.diretta-napoli.com/wp-content/uploads/2020/03/stadio-san-paolo-di-napoli-oggi.jpg",4.0f,"Giuseppe","Stadio San Paolo",40.827967f,14.193008f));
        listaRecensioniDatabase.add(new Recensioni("Grande la partita","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Stadio_San_Paolo_Panoramica_Champions_League.jpg/900px-Stadio_San_Paolo_Panoramica_Champions_League.jpg",4.0f,"Marco","Stadio San Paolo",40.827967f,14.193008f));
        listaRecensioniDatabase.add(new Recensioni("Che figata lo stadio nuovo","https://www.ilpost.it/wp-content/uploads/2019/06/stadio-san-paolo-napoli.jpg",3.9f,"Giuseppe","Stadio San Paolo",40.827967f,14.193008f));
        listaRecensioniDatabase.add(new Recensioni("Nice ;)","https://www.stadi.online/wp-content/uploads/sites/108/stadio-san-paolo-napoli-3d.jpg",4.1f,"Marco","Stadio San Paolo",40.827967f,14.193008f));

        listaRecensioniDatabase.add(new Recensioni("Non mi piace molto Pompei :(","https://upload.wikimedia.org/wikipedia/commons/7/7b/Via_dell%27Abbondanza_1.JPG",2.5f,"Giuseppe","Pompei",40.749183654785156f,14.500738143920898f));
        listaRecensioniDatabase.add(new Recensioni("Bella Pompei!!","https://upload.wikimedia.org/wikipedia/commons/7/7b/Via_dell%27Abbondanza_1.JPG",3.3f,"Marco","Pompei",40.749183654785156f,14.500738143920898f));

        listaRecensioniDatabase.add(new Recensioni("Molto bello castell dell'ovo","https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Castelo_do_Ovo.jpg/1200px-Castelo_do_Ovo.jpg",4.9f,"Giuseppe","Castel dell'ovo",40.827222f,14.248611f));

        listaRecensioniDatabase.add(new Recensioni("L'anfiteatro della mia città è figo!","https://lh3.googleusercontent.com/proxy/Ny7s7MCWrUe4y7QHrOPyytvEJ4X_B0l9ZIuOgOcckw9wUCwVlqO2UABcdU-eLnYMzQsRFWtbIZXiODRbXjF6EBeaZ26UqFjv2CiYCNNMxQyq-Fx22b2TltrdtoCRrCBLMDifi4sDfabOYkHMgE5OML1UDgu8",3.9f,"Giuseppe","Anfiteatro Flavio",40.82336f,14.12434f));
        listaRecensioniDatabase.add(new Recensioni("Ma come hanno fatto i romani a costruire tutto ciò? :O","https://www.beniculturali.it/mibac/multimedia/MiBAC/images/small/72/3a9bc83e2df59271821109b781461b781f44b.jpg",3.7f,"Marco","Anfiteatro Flavio",40.82336f,14.12434f));
        listaRecensioniDatabase.add(new Recensioni("Adoro le costruzioni Romane","https://www.lacooltura.com/wp-content/uploads/2015/04/Sotterranei-Anfiteatro-Flavio.jpeg",4.1f,"Marco","Anfiteatro Flavio",40.82336f,14.12434f));

        listaRecensioniDatabase.add(new Recensioni("Lo spettacolo era stupendo","https://www.teatrosancarlo.it/files/TSC03.jpg",5.0f,"Giuseppe","Teatro San Carlo",40.85299f,14.24789f));
        listaRecensioniDatabase.add(new Recensioni("Il teatro San Carlo è il più bello d'Italia!!","https://tuttodanzaweb.it/wp-content/uploads/2020/06/Teatro-San-Carlo-di-Napoli-Stagione-202021_.jpg",4.9f,"Giuseppe","Teatro San Carlo",40.85299f,14.24789f));
        listaRecensioniDatabase.add(new Recensioni("Non mi piace questo teatro","https://ecampania.it/wp-content/uploads/2020/03/teatro-san-carlo-napoli-1130x650.jpg",5.0f,"Marco","Teatro San Carlo",40.85299f,14.24789f));
        listaRecensioniDatabase.add(new Recensioni("Lo spettacolo era stupendo","https://www.farodiroma.it/wp-content/uploads/2019/12/101728121-1e70f7dd-9e10-48cc-9959-86e332b2ad28.jpg",4.5f,"Giuseppe","Teatro San Carlo",40.85299f,14.24789f));
        listaRecensioniDatabase.add(new Recensioni("Lo spettacolo era grandioso","https://www.napolidavivere.it/wp-content/uploads/bfi_thumb/Teatro-San-Carlo-festa-della-musica-5sbnlydbvd512i2xc32w0efmcx5drr9vtzi7755te44.jpg",4.5f,"Marco","Teatro San Carlo",40.85299f,14.24789f));
    }
}
