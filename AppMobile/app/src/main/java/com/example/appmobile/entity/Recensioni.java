package com.example.appmobile.entity;

public class Recensioni {

    private String testoRecensione;
    private String urlImmagine;
    private float valutazione;
    private String userNameUtente; //probabilmente sarà un id?
    private String nomeStruttura; //probabilmente sarà id?

    public Recensioni(String testoRecensione, String urlImmagine, float valutazione, String userNameUtente, String nomeStruttura) {
        this.testoRecensione = testoRecensione;
        this.urlImmagine = urlImmagine;
        this.valutazione = valutazione;
        this.userNameUtente = userNameUtente;
        this.nomeStruttura = nomeStruttura;
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
}
