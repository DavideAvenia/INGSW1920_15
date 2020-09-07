package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.R;
import com.example.appmobile.boundary.GestioneProfiloForm;

import java.util.Map;

public class GestioneProfiloController {
    private GestioneProfiloForm gestioneProfiloForm;
    private static GestioneProfiloController gestioneProfiloController = null;
    private String userIdLogged = MainFrameForm.getUserIdLogged();
    private boolean isLogged = MainFrameForm.getIsLogged();

    private GestioneProfiloController() {
    }

    public static GestioneProfiloController getGestioneProfiloController() {
        if (gestioneProfiloController == null) {
            gestioneProfiloController = new GestioneProfiloController();
        }
        return gestioneProfiloController;
    }

    public Map<String, String> trovaAttributiUtente() {
        Map<String, String> attributiUtente;
        attributiUtente = MainFrameForm.getAttributiUtenteLoggato();

        System.out.println(MainFrameForm.getAttributiUtenteLoggato());
        return attributiUtente;
    }

    public void cambioPassword(String oldPsw, String psw, Context context) {
        String service = context.getString(R.string.cloudService);
        UtenteDao u = DaoFactory.getUtenteDao(service, context);
        assert u != null;
        u.cambioPassword(oldPsw, psw, userIdLogged, context);
    }

    public void cambioMail(String mail, Context context) {
        String service = context.getString(R.string.cloudService);
        UtenteDao u = DaoFactory.getUtenteDao(service, context);
        assert u != null;
        u.cambioEmail(mail, userIdLogged, context);
    }

    public void cambioCell(String numCell, Context context) {
        String service = context.getString(R.string.cloudService);
        UtenteDao u = DaoFactory.getUtenteDao(service, context);
        assert u != null;
        u.cambioCell(numCell, userIdLogged, context);
    }

    public void mostraGestioneProfilo(Context context) {
        context.startActivity(new Intent(context, GestioneProfiloForm.class));
    }

    public void cambioNickname(Context context, String nuovoNick) {
        String service = context.getString(R.string.cloudService);
        UtenteDao u = DaoFactory.getUtenteDao(service, context);
        assert u != null;
        u.cambioNick(context,nuovoNick,userIdLogged);
    }

    public void setUseNickFalse(Context context){
        String service = context.getString(R.string.cloudService);
        UtenteDao u = DaoFactory.getUtenteDao(service,context);
        assert  u != null;
        u.setUseNickFalse(context,userIdLogged);
    }
}
