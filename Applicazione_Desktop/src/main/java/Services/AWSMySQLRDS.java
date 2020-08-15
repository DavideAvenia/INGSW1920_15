package Services;

import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AWSMySQLRDS implements StatisticheStruttureDAO {
    @Override
    public List<StatisticheStrutture> getStatisticheStruttureByFiltro(String filtro) {
        return null;
    }

    @Override
    public List<StatisticheStrutture> getAllStatisticheStrutture(List<StatisticheStrutture> L1) {
        L1 = new ArrayList<>();

        JSONObject tmp = new JSONObject();
        try {
            tmp.put("temp", "temp");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
        Request req = new Request.Builder().url("https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getallstatistichestrutture").post(requestBody).build();
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
    public void aggiornaStatistiche() {
        List<StatisticheStrutture> update = null;
        getAllStatisticheStrutture(update);
    }
}
