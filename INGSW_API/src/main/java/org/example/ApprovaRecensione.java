package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ApprovaRecensione implements RequestHandler<Map<String,String>,Boolean> {
    @Override
    public Boolean handleRequest(Map<String, String> requestyBody, Context context) {
        DatabaseConnection db = new DatabaseConnection();
        String usernameUtente = requestyBody.get("usernameUtente");
        String nomeStruttura = requestyBody.get("nomeStruttura");
        String latitudine = requestyBody.get("latitudine");
        String longitudine = requestyBody.get("longitudine");

        try {
            db.updateEntries("Update Recensioni Set Pending = 0 " +
                    "Where usernameUtente =\""+ usernameUtente +"\" AND nomeStruttura = \"" + nomeStruttura + "\" " +
                    "AND latitudine = \""+ latitudine + "\" AND longitudine = \"" + longitudine + "\";");

            db.updateEntries("UPDATE StatisticheUtenti SET livello = 1 WHERE numTotaleReviews >= 1 AND numTotaleReviews <10 AND userID = \"" + usernameUtente + "\";");
            db.updateEntries("UPDATE StatisticheUtenti SET livello = 2 WHERE numTotaleReviews >= 10 AND numTotaleReviews <50 AND userID = \"" + usernameUtente + "\";");
            db.updateEntries("UPDATE StatisticheUtenti SET livello = 3 WHERE numTotaleReviews >= 100 AND userID = \"" + usernameUtente + "\";");

            ResultSet result = db.eseguiQuery("Select avg(valutazione) as Somma From Recensioni where nomeStruttura = \"" + nomeStruttura +"\" " +
                    "AND latitudine = \"" + latitudine + "\" AND longitudine = \"" + longitudine +"\";");
            if(result.next()){
                String valutazioneMedia = result.getString("Somma");
                valutazioneMedia = valutazioneMedia.substring(0,3);
                db.updateEntries("UPDATE Strutture SET valutazioneMedia = \"" + valutazioneMedia + "\" Where nome = \""+ nomeStruttura +"\";");
                db.updateEntries("UPDATE StatisticheStrutture SET numReviews = numReviews + 1 Where nomeStruttura = \""+ nomeStruttura +"\";");
            }
            result.close();

            db.updateEntries("Update StatisticheUtenti SET numTotaleReviews = numTotaleReviews + 1 Where userID = \""+usernameUtente+"\";");
            ResultSet result2 = db.eseguiQuery("Select avg(valutazione) as Val from Recensioni Where usernameUtente = \""+usernameUtente+"\";");
            if(result2.next()){
                String valutazioneMediaUtente = result2.getString("Val");
                db.updateEntries("update StatisticheUtenti set avgScore = \""+valutazioneMediaUtente+"\" where userID = \""+usernameUtente+"\";");
            }
            result2.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;
    }
}
