package com.example.appmobile.Dao;

import com.example.appmobile.entity.Recensioni;

import java.util.List;

public interface RecensioniDao {

    String URLAPIGETRECENSIONIBYNOMESTRUTTURAPOSIZIONE = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getrecensionibynomestrutturaposizione";

    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, String latitudine, String longitudine);
}
