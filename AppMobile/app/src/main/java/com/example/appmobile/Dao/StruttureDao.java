package com.example.appmobile.Dao;

import com.example.appmobile.entity.Strutture;

import java.util.List;

public interface StruttureDao {

    String URLAPIGETSTRUTTUREBYFILTRI="https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getstrutturebyfiltri";
    String URLAPIGETSTRUTTURABYNOMEPOSIZIONE = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getstrutturebynomeposizione";
    String URLAPIINCREMENTANUMEROVISITATORI = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/incrementanumerovisitatori";


    public List<Strutture> getStruttureByFiltri(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String rangePrezzo);
    public Strutture getStrutturaByNomePosizione(String nome, String latitudine, String longitudine);
    public void incrementaNumeroVisitatori(String nome, String latitudine, String longitudine);
}
