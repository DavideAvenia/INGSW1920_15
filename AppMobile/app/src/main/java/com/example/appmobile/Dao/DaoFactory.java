package com.example.appmobile.Dao;

import android.content.Context;
import android.widget.Toast;

import com.example.appmobile.services.AWSCognito;
import com.example.appmobile.services.AWSMySQLRds;

public abstract class DaoFactory {

    public static UtenteDao getUtenteDao(String service, Context context){
        switch(service){
            case "AWS":
                return new AWSCognito(context);
            default:
                return null;

        }
    }

    public static StruttureDao getStruttureDao(String service, Context context){
        switch(service){
            case "AWS":
                return new AWSMySQLRds(context);
            default:
                return null;

        }
    }

    public static RecensioniDao getRecensioniDao(String service, Context context){

        switch(service){
            case "AWS":
                return new AWSMySQLRds(context);
            default:
                return null;

        }
    }
}
