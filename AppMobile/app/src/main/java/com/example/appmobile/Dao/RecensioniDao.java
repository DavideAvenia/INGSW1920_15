package com.example.appmobile.Dao;

import com.example.appmobile.entity.Recensioni;

import java.util.List;

public interface RecensioniDao {

    String URLAPIGETRECENSIONIBYNOMESTRUTTURAPOSIZIONE = "";

    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, String latitudine, String longitudine);
}
