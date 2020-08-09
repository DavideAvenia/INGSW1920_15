package com.example.appmobile.Dao;

import com.example.appmobile.entity.Recensioni;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface RecensioniDao {

    String URLAPIGETRECENSIONIBYNOMESTRUTTURAPOSIZIONE = "";
    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, LatLng posizione);
}
