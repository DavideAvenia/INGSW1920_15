package com.example.appmobile.controller;

import android.content.Context;

import com.example.appmobile.MainFrameForm;

public class ControllerLogin {

    private MainFrameForm mainFrameForm;

    private static ControllerLogin controllerLogin = null;

    private ControllerLogin(){

    }

    public ControllerLogin getControllerLogin(){
        if(controllerLogin == null){
            controllerLogin = new ControllerLogin();
        }
        return controllerLogin;
    }

    public void mostraLoginForm(Context context){
        //fare startActivity di LoginForm
    }

    public void mostraRegistrazioneForm(Context context){
        //fare startActivity di RegistrazioneForm
    }


}
