package com.example.appmobile.Dao;

import android.content.Context;

import com.example.appmobile.entity.StatisticheStrutture;
import com.example.appmobile.entity.Strutture;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface StruttureDao {

    public List<Strutture> getStruttureByFiltri(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String rangePrezzo);
    public Strutture getStrutturaByNomePosizione(String nome, LatLng posizione);
    public void incrementaNumeroVisitatori(String nome, LatLng posizione);
}
