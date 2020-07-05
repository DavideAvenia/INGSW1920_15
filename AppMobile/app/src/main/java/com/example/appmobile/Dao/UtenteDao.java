package com.example.appmobile.Dao;

import android.content.Context;

import com.example.appmobile.entity.Utente;

public interface UtenteDao {

    /***************OPERAZIONI DI LOGIN*****************/
    public void login(String userId, String password, Context context);
    public void registration(String userId, String nome,String cognome, String cellulare, String email, String password,Context context);
    public void recuperaCodiceResetPassword(final Context context, String userId);
    public void resetPassword(String code, String password,Context context);
    public void signout(String userId);
    public Utente getUtenteByUserId(String userId);
    //incrementaNumeroLogin();

    //Operazioni di utilità
    public void showToast(Context context, String messaggio);
}
