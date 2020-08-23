package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;

import com.example.appmobile.boundary.ScrivereRecensioniForm;

import java.util.ArrayList;

public class ScrivereRecensioniController {
    private static ScrivereRecensioniController recensioniController = null;

    private ScrivereRecensioniController(){}

    public static ScrivereRecensioniController getScrivereRecensioniController(){
        if(recensioniController == null)
            recensioniController = new ScrivereRecensioniController();
        return recensioniController;
    }

    public void mostraScrivereStrutture(Context context){
        context.startActivity(new Intent(context, ScrivereRecensioniForm.class));
    }

    public boolean inserisciRecensione(Context context, String testoRecensione, float valutazioneRecensione){
        //Il controller dovrebbe ricevere testo della recensione, le stelline che gli si vengono date e le immagini
        //quindi

        //Da aggiungere una ArrayList di media generici
        //AGGIUNGERE I PERMESSI DI ACCESSO AI MEDIA

        //Mi servono che l'utente sia necessariamente loggato, quindi collegarmi a Cognito
        //Mi servono le recensioni per poterne aggiungere una in più al DB ma che non sia pubblicata, per essere pubblica deve essere necessariamente pending = FALSE
        return false;
    }
}
