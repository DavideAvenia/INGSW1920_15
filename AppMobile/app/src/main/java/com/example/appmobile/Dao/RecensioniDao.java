package com.example.appmobile.Dao;

import com.example.appmobile.entity.Recensioni;

import java.io.File;
import java.util.List;

public interface RecensioniDao {

    String URLAPIGETRECENSIONIBYNOMESTRUTTURAPOSIZIONE = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getrecensionibynomestrutturaposizione";
    String URLAPIINSERTRECENSIONI = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/insertrecensioni";

    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, String latitudine, String longitudine);

    public boolean insertRecensioni(String nomeUtente, String nomeStruttura, String latitudine, String longitudine, String testoRecensione, float valutazione, String urlImmagine);

    public String insertImmagineS3(File file);
}
