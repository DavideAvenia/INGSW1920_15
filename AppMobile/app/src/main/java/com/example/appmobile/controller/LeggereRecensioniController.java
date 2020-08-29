package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.R;
import com.example.appmobile.boundary.RecensioniStruttureForm;
import com.example.appmobile.entity.Recensioni;
import com.example.appmobile.entity.Strutture;

import java.util.ArrayList;
import java.util.List;

public class LeggereRecensioniController {

    private static LeggereRecensioniController leggereRecensioniController = null;
    private StruttureDao struttureDao;
    private UtenteDao utenteDao;
    private RecensioniDao recensioniDao;

    private RecensioniStruttureForm recensioniStruttureForm;
    private MainFrameForm mainFrameForm;


    private LeggereRecensioniController() {
    }

    public static LeggereRecensioniController getLeggereRecensioniController() {

        if (leggereRecensioniController == null) {
            leggereRecensioniController = new LeggereRecensioniController();
        }
        return leggereRecensioniController;
    }

    public void mostraRecensioniStrutture(String nomeStruttura, String latitudine, String longitudine, Context context, String userIdLogged) {

        String service = context.getString(R.string.cloudService);

        /*Recupero dei dao necessari*/
        recensioniDao = DaoFactory.getRecensioniDao(service, context);
        utenteDao = DaoFactory.getUtenteDao(service, context);
        struttureDao = DaoFactory.getStruttureDao(service, context);

        /*Liste delle informazioni da passare all'activity RecensioniStruttureForm*/
        ArrayList<String> nomiRecensiori = new ArrayList<String>();
        ArrayList<String> listaUrlFoto = new ArrayList<String>();
        ArrayList<String> listaTestiRecensioni = new ArrayList<String>();
        String descrizione = null;
        float valutasione;

        List<Recensioni> listaRecensioni = recensioniDao.getRecensioniByNomeStrutturaPosizione(nomeStruttura, latitudine, longitudine);
        float listaValutazioni[] = new float[listaRecensioni.size()];

        /*Recupero di tutte le informazioni della Struttra corrente*/
        Strutture struttura = struttureDao.getStrutturaByNomePosizione(nomeStruttura, latitudine, longitudine);
        descrizione = struttura.getDescrizione();
        struttureDao.incrementaNumeroVisitatori(nomeStruttura, latitudine, longitudine);
        valutasione = struttura.getValutazioneMedia();

        int k = 0;
        for (Recensioni r : listaRecensioni) {
            String userId = r.getUserNameUtente();

            nomiRecensiori.add(userId);
            utenteDao.recuperaNameToShowUtente(userId, k);


            listaUrlFoto.add(r.getUrlImmagine());
            listaTestiRecensioni.add(r.getTestoRecensione());
            listaValutazioni[k++] = r.getValutazione();
        }



        /*Creazione intent e passaggio tramite esso di tutte le informazioni raccolte della struttura corrente*/
        Intent intent = new Intent(context, RecensioniStruttureForm.class);
        intent.putExtra("nomeStruttura", nomeStruttura);
        intent.putExtra("latitudine", latitudine);
        intent.putExtra("longitudine", longitudine);
        intent.putExtra("valutazione", valutasione);
        intent.putExtra("descrizione", descrizione);
        intent.putExtra("nomiRecensori", nomiRecensiori);
        intent.putExtra("listaUrlFoto", listaUrlFoto);
        intent.putExtra("listaTestiRecensioni", listaTestiRecensioni);
        intent.putExtra("listaValutazioni", listaValutazioni);
        intent.putExtra("userIdLogged", userIdLogged);

        context.startActivity(intent);
    }


    public void notifyNameToShow(String nameToShow, int position) {
        RecensioniStruttureForm.updateAdapter(nameToShow, position);
    }
}
