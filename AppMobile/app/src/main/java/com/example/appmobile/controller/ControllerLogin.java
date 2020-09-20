package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.R;
import com.example.appmobile.boundary.LoginForm;
import com.example.appmobile.boundary.RegistrazioneForm;
import com.example.appmobile.boundary.ResetPasswordForm;

import java.util.Map;
import java.util.regex.Pattern;

public class ControllerLogin {

    private static ControllerLogin controllerLogin = null;
    private MainFrameForm mainFrameForm;
    private LoginForm loginForm;
    private RegistrazioneForm registrazioneForm;
    private ResetPasswordForm resetPasswordForm;
    private UtenteDao servizioUtente;

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

        servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.registration(userId, nome, cognome, cellulare, email, password, context);
    }

    public void richiediCodiceResetPassword(Context context, String userId) {
        String service = context.getString(R.string.cloudService);

        servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.recuperaCodiceResetPassword(context, userId);
    }

    public void resetPassword(Context context, String code, String password) {
        String service = context.getString(R.string.cloudService);

        servizioUtente = DaoFactory.getUtenteDao(service, context);

        servizioUtente.resetPassword(code, password, context);
    }

    public void signout(Context context, String userId) {
        String service = context.getString(R.string.cloudService);

        servizioUtente = DaoFactory.getUtenteDao(service, context);

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

    public static boolean checkEmail(String email) {

        String tokens[] = email.split("_");
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || tokens[0].equals("admin")) {
            return false;
        }
        return true;
    }

    public boolean checkUsername(String username) {
        String tokens[] = username.split("_");
        if (tokens[0].equals("admin")) {
            return false;
        }
        return true;
    }

    public boolean checkPassword(String password) {
        final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$");

        if (PASSWORD_PATTERN.matcher(password).matches()) {
            return true;
        }
        return false;
    }


}
