package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.R;
import com.example.appmobile.boundary.ScriviRecensioniForm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScriviRecensioniController {
    private static ScriviRecensioniController recensioniController = null;
    private boolean isLogged;
    private String nomeStruttura;
    private String latitudine;
    private String longitudine;
    private String userIdLogged;

    private ScriviRecensioniController() {
    }

    public static ScriviRecensioniController getScriviRecensioniController() {
        if (recensioniController == null)
            recensioniController = new ScriviRecensioniController();
        return recensioniController;
    }

    public void mostraScrivereRecensioni(Context context, String nomeS, String lat, String longi, String userIdLogged) {
        isLogged = MainFrameForm.getIsLogged();
        if (isLogged) {
            context.startActivity(new Intent(context, ScriviRecensioniForm.class));
            nomeStruttura = nomeS;
            latitudine = lat;
            longitudine = longi;
            this.userIdLogged = userIdLogged;
        } else {
            Toast.makeText(context, "Devi effettuare prima il login", Toast.LENGTH_SHORT).show();
        }
    }


    public void inserisciRecensione(Context context, String testoRecensione, float valutazioneRecensione, File immagine) {

        String service = context.getString(R.string.cloudService);

        RecensioniDao recensioniDao = DaoFactory.getRecensioniDao(service, context);

        if (immagine == null)
            recensioniDao.insertRecensioni(userIdLogged, nomeStruttura, latitudine, longitudine, testoRecensione, valutazioneRecensione, "");
        else {
            //Inserimento dell'immagine in S3, prendi l'URL e chiami
            String immagineURL = recensioniDao.insertImmagineS3(immagine);
            recensioniDao.insertRecensioni(userIdLogged, nomeStruttura, latitudine, longitudine, testoRecensione, valutazioneRecensione, immagineURL);

        }
    }



    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }
}
