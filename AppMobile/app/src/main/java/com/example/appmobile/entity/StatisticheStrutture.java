package com.example.appmobile.entity;

public class StatisticheStrutture {
    private int numVisitatori;
    private int numReviews;
    private int numClienti;
    private String nome;
    private String longitudine;
    private String latitudine;

    public StatisticheStrutture(int numVisitatori, int numReviews, int numClienti, String nome, String longitudine, String latitudine) {
        this.numVisitatori = numVisitatori;
        this.numReviews = numReviews;
        this.numClienti = numClienti;
        this.nome = nome;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    public StatisticheStrutture() {

    }

    public int getNumVisitatori() {
        return numVisitatori;
    }

    public void setNumVisitatori(int numVisitatori) {
        this.numVisitatori = numVisitatori;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public int getNumClienti() {
        return numClienti;
    }

    public void setNumClienti(int numClienti) {
        this.numClienti = numClienti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }
}
