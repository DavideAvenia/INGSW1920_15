package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.R;
import com.example.appmobile.boundary.RecensioniStruttureForm;
import com.example.appmobile.entity.Recensioni;
import com.example.appmobile.entity.Utente;
import com.google.android.gms.maps.model.LatLng;

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

    public void mostraRecensioniStrutture(String nomeStruttura, LatLng posizione, Context context){

        String service = context.getString(R.string.cloudService);

        RecensioniDao recensioniDao = DaoFactory.getRecensioniDao(service,context);
        UtenteDao utenteDao = DaoFactory.getUtenteDao(service,context);

        List<Recensioni> listaRecensioni = recensioniDao.getRecensioniByNomeStrutturaPosizione(nomeStruttura,posizione);

        String name;
        for(Recensioni r:listaRecensioni){
            Utente u = utenteDao.getUtenteByUserId(r.getUserNameUtente());

            if(u.isUseNick()){
                name = u.getNickname();
            }else{
                r.getUserNameUtente();
            }
        }

        Intent intent = new Intent(context, RecensioniStruttureForm.class);
        intent.putExtra("nomeStruttura",nomeStruttura);
        intent.putExtra("latitudine",posizione.latitude);
        intent.putExtra("longitudine",posizione.longitude);

        context.startActivity(intent);
    }
}
