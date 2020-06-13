package com.example.appmobile.services;

import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.entity.Strutture;

import java.util.List;

public class AWSMySQLRds implements StruttureDao {

    @Override
    public List<Strutture> getStruttureByFiltri(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String rangePrezzo) {
        return null;
    }
}
