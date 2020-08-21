package org.example.Entity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllStatisticheUtenti implements RequestHandler<Map<String,String>,List<StatisticheUtenti>> {


    @Override
    public List<StatisticheUtenti> handleRequest(Map<String, String> stringStringMap, Context context) {
        List<StatisticheUtenti> StatUtenti = new ArrayList<StatisticheUtenti>();
        DatabaseConnection DB2 = new DatabaseConnection();
        try{
            ResultSet res2 = DB2.eseguiQuery("select * from StatisticheUtenti");
            while(res2.next()){
                int numUtenti = res2.getInt(1);
                int percentageNumReviews = res2.getInt(2);
                int avgScore = res2.getInt(3);
                int loginCounter = res2.getInt(4);
                int numTotaleReviews = res2.getInt(5);
                StatisticheUtenti SU = new StatisticheUtenti(numUtenti,percentageNumReviews,avgScore,loginCounter,numTotaleReviews);
                StatUtenti.add(SU);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return StatUtenti;
    }
}
