package org.example.Entity;

public class Strutture {


    private String nome;
    private String città;
    private float valutazioneMedia;
    private String maxPrezzo;
    private String orarioApertura;
    private String categoria;
    private String latitudine;
    private String longitudine;
    private String descrizione;

    public Strutture(String nome, String città, float valutazioneMedia, String maxPrezzo, String orarioApertura, String categoria, String latitudine, String longitudine, String descrizione) {
        this.nome = nome;
        this.città = città;
        this.valutazioneMedia = valutazioneMedia;
        this.maxPrezzo = maxPrezzo;
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

    public String getMaxPrezzo() {
        return maxPrezzo;
    }

    public void setMaxPrezzo(String maxPrezzo) {
        this.maxPrezzo = maxPrezzo;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
