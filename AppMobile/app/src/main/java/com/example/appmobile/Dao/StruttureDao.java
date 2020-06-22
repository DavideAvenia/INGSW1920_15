package com.example.appmobile.Dao;

import com.example.appmobile.entity.Strutture;

import java.util.List;

public interface StruttureDao {

    public List<Strutture> getStruttureByFiltri(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String rangePrezzo);
}
