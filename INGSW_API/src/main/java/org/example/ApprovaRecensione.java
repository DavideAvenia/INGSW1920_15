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

        db.updateEntries("Update Recensioni Set Pending = 0 " +
                "Where usernameUtente =\""+ usernameUtente +"\" AND nomeStruttura = \"" + nomeStruttura + "\" " +
                "AND latitudine = \""+ latitudine + "\" AND longitudine = \"" + longitudine + "\";");

        db.updateEntries("UPDATE StatisticheUtenti SET livello = 1 WHERE numTotaleReviews >= 1 AND numTotaleReviews <10 AND userID = \"" + usernameUtente + "\";");
        db.updateEntries("UPDATE StatisticheUtenti SET livello = 2 WHERE numTotaleReviews >= 10 AND numTotaleReviews <50 AND userID = \"" + usernameUtente + "\";");
        db.updateEntries("UPDATE StatisticheUtenti SET livello = 3 WHERE numTotaleReviews >= 100 AND userID = \"" + usernameUtente + "\";");

        try {
            ResultSet result = db.eseguiQuery("Select avg(valutazione) as Somma From Recensioni where nomeStruttura = \"" + nomeStruttura +"\" " +
                    "AND latitudine = \"" + latitudine + "\" AND longitudine = \"" + longitudine +"\";");
            if(result.next()){
                String valutazioneMedia = result.getString("Somma");
                valutazioneMedia = valutazioneMedia.substring(0,3);
                db.updateEntries("UPDATE Strutture SET valutazioneMedia = \"" + valutazioneMedia + "\" Where nome = \""+ nomeStruttura +"\";");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }
}
