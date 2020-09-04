package Services;

import DAO.RecensioniDAO;
import DAO.StatisticheStruttureDAO;
import DAO.StatisticheUtentiDAO;
import Entity.Recensioni;
import Entity.StatisticheStrutture;
import Entity.StatisticheUtenti;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AWSMySQLRDS implements StatisticheStruttureDAO, StatisticheUtentiDAO, RecensioniDAO {

    @Override
    public List<StatisticheStrutture> getAllStatisticheStrutture() {
        List<StatisticheStrutture> L1 = new ArrayList<>();

        JSONObject tmp = new JSONObject();
        try {
            tmp.put("temp", "temp");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
        Request req = new Request.Builder().url(APIGETALLSTATISTICHESTRUTTURE).post(requestBody).build();
        Response res = null;
        try {
            res = client.newCall(req).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res.isSuccessful()) {
            try {
                JSONArray a1 = new JSONArray(res.body().string());
                for (int i = 0; i < a1.length(); i++) {
                    JSONObject ob1 = a1.getJSONObject(i);
                    String nomeStruttura = ob1.get("nome").toString();
                    String latitudine = ob1.get("latitudine").toString();
                    String longitudine = ob1.get("longitudine").toString();
                    int numeroReviews = Integer.parseInt(ob1.get("numReviews").toString());
                    int numClient = Integer.parseInt(ob1.get("numClienti").toString());
                    int numVisitatori = Integer.parseInt(ob1.get("numVisitatori").toString());
                    StatisticheStrutture S1 = new StatisticheStrutture(numVisitatori, numeroReviews, numClient, nomeStruttura, longitudine, latitudine);
                    L1.add(S1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return L1;
    }

    @Override
    public List<StatisticheUtenti> getStats() {
        List<StatisticheUtenti> userstats = new ArrayList<StatisticheUtenti>();

        JSONObject tmp = new JSONObject();
        try {
            tmp.put("temp", "temp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
        Request req = new Request.Builder().url(APIGETALLSTATISTICHEUTENTI).post(requestBody).build();
        Response res = null;
        try {
            res = client.newCall(req).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res.isSuccessful()) {
            try {
                JSONArray a1 = new JSONArray(res.body().string());
                for (int i = 0; i < a1.length(); i++) {
                    JSONObject ob1 = a1.getJSONObject(i);
                    String userID = ob1.get("userID").toString();
                    int livello = ob1.getInt("livello");
                    float agvScore = ob1.getFloat("avgScore");
                    int loginCounter = ob1.getInt("loginCounter");
                    int numTotaleReviews = ob1.getInt("numTotReviews");
                    StatisticheUtenti S1 = new StatisticheUtenti(userID, livello, agvScore, loginCounter, numTotaleReviews);
                    userstats.add(S1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userstats;

    }

    @Override
    public void deleteStatisticheUtente(String userId) {

        JSONObject tmp = new JSONObject();
        try {
            tmp.put("userId", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
        Request req = new Request.Builder().url(APIDELETESTATISTICHEUTENTE).post(requestBody).build();

        Response res = null;
        try {
            res = client.newCall(req).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recensioni> getAllRecensioniByPending(){
        List<Recensioni> listaRecensioni = new ArrayList<Recensioni>();

        JSONObject tmp = new JSONObject();
        try {
            tmp.put("temp", "temp");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
        Request req = new Request.Builder().url(APIGETALLRECENSIONIBYPENDING).post(requestBody).build();
        Response res = null;
        try {
            res = client.newCall(req).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (res.isSuccessful()) {
            try{
                JSONArray arrayRecensioni = new JSONArray(res.body().string());
                for (int i = 0; i < arrayRecensioni.length(); i++) {
                    JSONObject obRecensione = arrayRecensioni.getJSONObject(i);
                    String testoRecensione = obRecensione.getString("testoRecensione");
                    String urlImmagine = obRecensione.getString("urlImmagine");
                    float valutazione = obRecensione.getFloat("valutazione");
                    String usernameUtente = obRecensione.getString("userNameUtente");
                    String nomeStruttura = obRecensione.getString("nomeStruttura");
                    double latitudine = obRecensione.getFloat("latitudine");
                    double longitudine = obRecensione.getFloat("longitudine");
                    Recensioni r = new Recensioni(testoRecensione, urlImmagine, valutazione, usernameUtente, nomeStruttura, latitudine, longitudine, true);
                    listaRecensioni.add(r);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return listaRecensioni;
    }

    //Faranno un Update in sql
    //Mi servono le chiavi per accedere alla recensione
    @Override
    public boolean approvaRecensione() {
        return false;
    }

    @Override
    public boolean disapprovaRecensione() {
        return false;
    }
}
