package com.example.appmobile.entity;

public class StatisticheStrutture {

    private String nomeStruttura;
    private float latitudine;
    private float longitudine;
    private int numeroVisitatori;
    private double valutazioneMedia;

    public StatisticheStrutture(String nomeStruttura, float latitudine, float longitudine, int numeroVisitatori, double valutazioneMedia) {
        this.nomeStruttura = nomeStruttura;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numeroVisitatori = numeroVisitatori;
        this.valutazioneMedia = valutazioneMedia;
    }

    public String getNomeStruttura() {
        return nomeStruttura;
    }

    public void setNomeStruttura(String nomeStruttura) {
        this.nomeStruttura = nomeStruttura;
    }

    public int getNumeroVisitatori() {
        return numeroVisitatori;
    }

    public void setNumeroVisitatori(int numeroVisitatori) {
        this.numeroVisitatori = numeroVisitatori;
    }

    public double getValutazioneMedia() {
        return valutazioneMedia;
    }

    public void setValutazioneMedia(double valutazioneMedia) {
        this.valutazioneMedia = valutazioneMedia;
    }

    public float getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(float latitudine) {
        this.latitudine = latitudine;
    }

    public float getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(float longitudine) {
        this.longitudine = longitudine;
    }
}
