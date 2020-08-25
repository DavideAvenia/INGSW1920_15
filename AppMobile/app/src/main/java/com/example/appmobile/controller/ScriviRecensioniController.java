package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.R;
import com.example.appmobile.boundary.ScriviRecensioniForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ScriviRecensioniController {
    private static ScriviRecensioniController recensioniController = null;
    private boolean isLogged = MainFrameForm.getIsLogged();

    private ScriviRecensioniController(){}

    public static ScriviRecensioniController getScriviRecensioniController(){
        if(recensioniController == null)
            recensioniController = new ScriviRecensioniController();
        return recensioniController;
    }

    public void mostraScrivereRecensioni(Context context){
        if(isLogged) {
            context.startActivity(new Intent(context, ScriviRecensioniForm.class));
        }
        Toast.makeText(context, "Devi effettuare prima il login", Toast.LENGTH_SHORT).show();
    }

    public boolean inserisciRecensione(Context context, String testoRecensione, float valutazioneRecensione, File immagine){
        //Il controller dovrebbe ricevere testo della recensione, le stelline che gli si vengono date e le immagini
        //quindi
        String service = context.getString(R.string.cloudService);

        RecensioniDao recensioniDao = DaoFactory.getRecensioniDao(service,context);
        UtenteDao utenteDao = DaoFactory.getUtenteDao(service,context);
        StruttureDao struttureDao = DaoFactory.getStruttureDao(service,context);


        //Mi servono che l'utente sia necessariamente loggato, quindi collegarmi a Cognito
        //Mi servono le recensioni per poterne aggiungere una in più al DB ma che non sia pubblicata, per essere pubblica deve essere necessariamente pending = FALSE
        return false;
    }

    public File getImmagineFromInput(Context context, Intent data)throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(data.getData());
        File file = new File(String.valueOf(inputStream));
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        File targetFile = new File(String.valueOf(inputStream));
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return targetFile;
    }
}