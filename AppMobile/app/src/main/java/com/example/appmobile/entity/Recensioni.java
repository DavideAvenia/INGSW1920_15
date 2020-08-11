package com.example.appmobile.entity;

public class Recensioni {

    private String testoRecensione;
    private String urlImmagine;
    private float valutazione;
    private String userNameUtente; //nel database è la chiave secondaria che si associa all'utente corrispondente

    /*Nel database costituiscono una chiave secondaria alla struttura corrispondente*/
    private String nomeStruttura;
    private Double latitudine;
    private Double longitudine;

    public Recensioni(String testoRecensione, String urlImmagine, float valutazione, String userNameUtente, String nomeStruttura, Double latitudine, Double longitudine) {
        this.testoRecensione = testoRecensione;
        this.urlImmagine = urlImmagine;
        this.valutazione = valutazione;
        this.userNameUtente = userNameUtente;
        this.nomeStruttura = nomeStruttura;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getTestoRecensione() {
        return testoRecensione;
    }

    public void setTestoRecensione(String testoRecensione) {
        this.testoRecensione = testoRecensione;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    public float getValutazione() {
        return valutazione;
    }

    public void setValutazione(float valutazione) {
        this.valutazione = valutazione;
    }

    public String getUserNameUtente() {
        return userNameUtente;
    }

    public void setUserNameUtente(String userNameUtente) {
        this.userNameUtente = userNameUtente;
    }

    public String getNomeStruttura() {
        return nomeStruttura;
    }

    public void setNomeStruttura(String nomeStruttura) {
        this.nomeStruttura = nomeStruttura;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }
}
