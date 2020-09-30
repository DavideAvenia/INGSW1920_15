package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.Entity.ModelloStatisticheEStrutture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllStatisticheStrutture implements RequestHandler<Map<String, String>, List<ModelloStatisticheEStrutture>> {
    @Override
    public List<ModelloStatisticheEStrutture> handleRequest(Map<String, String> request, Context context) {
        List<ModelloStatisticheEStrutture> Lista1 = new ArrayList<ModelloStatisticheEStrutture>();
        DatabaseConnection DB = new DatabaseConnection();
        try {
            ResultSet res = DB.eseguiQuery("SELECT * FROM StatisticheStrutture as ST inner join Strutture AS S ON ST.nomeStruttura = S.nome;");
            while (res.next()){
             String nomeStruttura = res.getString(1);
             String latitudine = res.getString(2);
             String longitudine = res.getString(3);
             int numClienti = res.getInt(5);
             int numVisitatori = res.getInt(6);
             String città = res.getString("città");
             String valutazioneMedia = res.getString("valutazioneMedia");
             String orarioApertura = res.getString("orarioApertura");
             String categoria = res.getString("categoria");

             ResultSet res2 = DB.eseguiQuery("SELECT count(usernameUtente) as righe\n" +
                        "FROM Recensioni\n" +
                        "WHERE pending = 0 and nomeStruttura = \""+ nomeStruttura +"\";");

             int numeroReviews = res2.getInt(1);
             ModelloStatisticheEStrutture S = new ModelloStatisticheEStrutture(numVisitatori,numeroReviews,numClienti,nomeStruttura,longitudine,latitudine,categoria,valutazioneMedia,orarioApertura,città);
             Lista1.add(S);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Lista1;
    }
}
