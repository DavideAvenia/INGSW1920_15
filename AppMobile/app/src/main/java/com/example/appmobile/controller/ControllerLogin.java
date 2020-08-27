package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.R;
import com.example.appmobile.boundary.LoginForm;
import com.example.appmobile.boundary.RegistrazioneForm;
import com.example.appmobile.boundary.ResetPasswordForm;

import java.util.Map;

public class ControllerLogin {

    private static ControllerLogin controllerLogin = null;
    private MainFrameForm mainFrameForm;
    private LoginForm loginForm;
    private RegistrazioneForm registrazioneForm;

    private ControllerLogin() {

    }

    public static ControllerLogin getControllerLogin() {
        if (controllerLogin == null) {
            controllerLogin = new ControllerLogin();
        }
        return controllerLogin;
    }

    public void mostraLoginForm(Context context) {
        context.startActivity(new Intent(context, LoginForm.class));
    }

    public void mostraRegistrazioneForm(Context context) {
        context.startActivity(new Intent(context, RegistrazioneForm.class));
    }

    public void mostraResetPasswordForm(Context context) {
        context.startActivity(new Intent(context, ResetPasswordForm.class));
    }

    public void login(String userId, String password, Context context) {
        String service = context.getString(R.string.cloudService);

        UtenteDao servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.login(userId, password, context);
    }

    public void registrazione(String userId, String nome, String cognome, String cellulare, String email, String password, Context context) {
        String service = context.getString(R.string.cloudService);

        UtenteDao servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.registration(userId, nome, cognome, cellulare, email, password, context);
    }

    public void richiediCodiceResetPassword(Context context, String userId) {
        String service = context.getString(R.string.cloudService);

        UtenteDao servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.recuperaCodiceResetPassword(context, userId);
    }

    public void resetPassword(Context context, String code, String password) {
        String service = context.getString(R.string.cloudService);

        UtenteDao servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.resetPassword(code, password, context);
    }

    public void signout(Context context, String userId) {
        String service = context.getString(R.string.cloudService);

        UtenteDao servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.signout(userId);
    }

    public void setIsLogged() {
        MainFrameForm.setIsLogged(true);
    }

    public void setUserIdLogged(String userIdLogged) {
        MainFrameForm.setUserIdLogged(userIdLogged);
    }

    public void setUtenteLogged(Map<String, String> attributiUtenteLoggato) {
        MainFrameForm.setAtributiUtenteLoggato(attributiUtenteLoggato);
    }


}
