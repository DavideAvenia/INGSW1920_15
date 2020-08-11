package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.R;
import com.example.appmobile.boundary.RecensioniStruttureForm;
import com.example.appmobile.entity.Recensioni;
import com.example.appmobile.entity.Strutture;
import com.example.appmobile.entity.Utente;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class LeggereRecensioniController {

    private static LeggereRecensioniController leggereRecensioniController = null;

    private LeggereRecensioniController(){}

    public static LeggereRecensioniController getLeggereRecensioniController(){

        if(leggereRecensioniController == null){
            return new LeggereRecensioniController();
        }else{
            return leggereRecensioniController;
        }
    }

    public void mostraRecensioniStrutture(String nomeStruttura, String latitudine, String longitudine, Context context){

        String service = context.getString(R.string.cloudService);

        /*Recupero dei dao necessari*/
        RecensioniDao recensioniDao = DaoFactory.getRecensioniDao(service,context);
        UtenteDao utenteDao = DaoFactory.getUtenteDao(service,context);
        StruttureDao struttureDao = DaoFactory.getStruttureDao(service,context);

        /*Liste delle informazioni da passare all'activity RecensioniStruttureForm*/
        ArrayList<String> nomiRecensiori = new ArrayList<String>();
        ArrayList<String> listaUrlFoto = new ArrayList<String>();
        ArrayList<String> listaTestiRecensioni = new ArrayList<String>();
        String descrizione = null;
        float valutasione;

        List<Recensioni> listaRecensioni = recensioniDao.getRecensioniByNomeStrutturaPosizione(nomeStruttura,latitudine,longitudine);
        float listaValutazioni[] = new float[listaRecensioni.size()];

        int k=0;
        for(Recensioni r:listaRecensioni){

            /*****************Siccome per i test non esistono gli utenti delle recensioni, per adesso non recuperiamo il nickname*********************/
            //nomiRecensiori.add(getNickName(r,utenteDao));
            nomiRecensiori.add(r.getUserNameUtente());
            /***********************************************************************/

            listaUrlFoto.add(r.getUrlImmagine());
            listaTestiRecensioni.add(r.getTestoRecensione());
            listaValutazioni[k++] = r.getValutazione();
        }

        /*Recupero di tutte le informazioni della Struttra corrente*/
        Strutture struttura = struttureDao.getStrutturaByNomePosizione(nomeStruttura,latitudine,longitudine);
        descrizione = struttura.getDescrizione();
        struttureDao.incrementaNumeroVisitatori(nomeStruttura,latitudine,longitudine);
        valutasione = struttura.getValutazioneMedia();

        /*Creazione intent e passaggio tramite esso di tutte le informazioni raccolte della struttura corrente*/
        Intent intent = new Intent(context, RecensioniStruttureForm.class);
        intent.putExtra("nomeStruttura",nomeStruttura);
        intent.putExtra("latitudine",latitudine);
        intent.putExtra("longitudine",longitudine);
        intent.putExtra("valutazione",valutasione);
        intent.putExtra("descrizione",descrizione);
        intent.putExtra("nomiRecensori",nomiRecensiori);
        intent.putExtra("listaUrlFoto",listaUrlFoto);
        intent.putExtra("listaTestiRecensioni",listaTestiRecensioni);
        intent.putExtra("listaValutazioni",listaValutazioni);

        context.startActivity(intent);
    }

    private String getNickName(Recensioni recensione, UtenteDao utenteDao){
        Utente utente = utenteDao.getUtenteByUserId(recensione.getUserNameUtente());

        if(utente.isUseNick()){
            return utente.getNickname();
        }else{
            return utente.getUserId();
        }
    }
}
