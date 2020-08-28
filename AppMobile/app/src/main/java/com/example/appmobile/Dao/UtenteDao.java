package com.example.appmobile.Dao;

import android.content.Context;

import com.example.appmobile.controller.ControllerLogin;
import com.example.appmobile.controller.LeggereRecensioniController;

public interface UtenteDao {

    ControllerLogin controllerLogin = ControllerLogin.getControllerLogin();
    LeggereRecensioniController leggereRecensioniController = LeggereRecensioniController.getLeggereRecensioniController();

    /***************OPERAZIONI DI LOGIN*****************/
    //le richieste al cloud vengono tutte gestite tramite callback che non ritornano valori.
    //Per tal motivo tutte le funzioni ritornano void
    public void login(String userId, String password, Context context);

    public void registration(String userId, String nome, String cognome, String cellulare, String email, String password, Context context);

    public void recuperaCodiceResetPassword(final Context context, String userId);

    public void resetPassword(String code, String password, Context context);

    public void signout(String userId);

    public void recuperaNameToShowUtente(String userId, int position);

    public void cambioPassword(String oldPsw, String psw, String userId, Context context);

    public void cambioEmail(String email, String userId,  Context context);

    public void cambioCell(String numCell, String userId,  Context context);

    //Operazioni di utilità
    public void showToast(Context context, String messaggio);
}
