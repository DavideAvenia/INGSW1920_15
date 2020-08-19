package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.Entity.StatisticheStrutture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllStatisticheStrutture implements RequestHandler<Map<String, String>, List<StatisticheStrutture>> {
    @Override
    public List<StatisticheStrutture> handleRequest(Map<String, String> stringStringMap, Context context) {
        List<StatisticheStrutture> Lista1 = new ArrayList<StatisticheStrutture>();
        DatabaseConnection DB = new DatabaseConnection();
        try {
            ResultSet res = DB.eseguiQuery("select * from StatisticheStrutture");
            while (res.next()){
             String nomeStruttura = res.getString(1);
             String latitudine = res.getString(2);
             String longitudine = res.getString(3);
             int numeroReviews = res.getInt(4);
             int numClienti = res.getInt(5);
             int numVisitatori = res.getInt(6);
             StatisticheStrutture S = new StatisticheStrutture(numVisitatori,numeroReviews,numClienti,nomeStruttura,longitudine,latitudine);
             Lista1.add(S);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Lista1;
    }
}