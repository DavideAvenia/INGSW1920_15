package com.example.appmobile.entity;

public class Strutture {


    private String nome;
    private String città;
    private float valutazioneMedia;
    private String rangePrezzo;
    private String orarioApertura;
    private String categoria;
    private float latitudine;
    private float longitudine;
    private String descrizione;

    public Strutture(String nome, String città, float valutazioneMedia, String rangePrezzo, String orarioApertura, String categoria, float latitudine, float longitudine, String descrizione) {
        this.nome = nome;
        this.città = città;
        this.valutazioneMedia = valutazioneMedia;
        this.rangePrezzo = rangePrezzo;
        this.orarioApertura = orarioApertura;
        this.categoria = categoria;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public float getValutazioneMedia() {
        return valutazioneMedia;
    }

    public void setValutazioneMedia(float valutazioneMedia) {
        this.valutazioneMedia = valutazioneMedia;
    }

    public String getRangePrezzo() {
        return rangePrezzo;
    }

    public void setRangePrezzo(String rangePrezzo) {
        this.rangePrezzo = rangePrezzo;
    }

    public String getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(String orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
