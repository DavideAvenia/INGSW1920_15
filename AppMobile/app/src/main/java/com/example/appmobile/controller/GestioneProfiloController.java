package com.example.appmobile.controller;

import com.example.appmobile.MainFrameForm;

import java.util.Map;

public class GestioneProfiloController {
    private String userIdLogged = MainFrameForm.getUserIdLogged();
    private boolean isLogged = MainFrameForm.getIsLogged();
    private static GestioneProfiloController gestioneProfiloController = null;

    private GestioneProfiloController(){};

    public static GestioneProfiloController getGestioneProfiloController() {
        if(gestioneProfiloController == null){
            gestioneProfiloController = new GestioneProfiloController();
        }
        return gestioneProfiloController;
    }

    public Map<String, String> trovaNomeUtente(){
        Map<String,String> attributiUtente;
        attributiUtente = MainFrameForm.getAttributiUtenteLoggato();
        return attributiUtente;
    }
}
