package com.example.appmobile.Dao;

import android.content.Context;

public interface UtenteDao {

    public void login(String userId, String password, Context context);
    public void registration(String userId, String nome,String cognome, String cellulare, String email, String password,Context context);
    public void showToast(Context context, String messaggio);
    public void recuperaPassword();
}
