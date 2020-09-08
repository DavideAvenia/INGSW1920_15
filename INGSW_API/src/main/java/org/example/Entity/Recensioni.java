package org.example.Entity;

public class Recensioni {

    private String testoRecensione;
    private String urlImmagine;
    private float valutazione;
    private String userNameUtente; //nel database è la chiave secondaria che si associa all'utente corrispondente

    /*Nel database costituiscono una chiave secondaria alla struttura corrispondente*/
    private String nomeStruttura;
    private String latitudine;
    private String longitudine;

    private boolean pending;

    public Recensioni(String testoRecensione, String urlImmagine, float valutazione, String userNameUtente, String nomeStruttura, String latitudine, String longitudine) {
        this.testoRecensione = testoRecensione;
        this.urlImmagine = urlImmagine;
        this.valutazione = valutazione;
        this.userNameUtente = userNameUtente;
        this.nomeStruttura = nomeStruttura;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.pending = true;
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

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }
}
