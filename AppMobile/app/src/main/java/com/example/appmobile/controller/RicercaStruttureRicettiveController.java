package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.SystemClock;

import com.example.appmobile.Dao.DaoFactory;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.MainFrameForm;
import com.example.appmobile.R;
import com.example.appmobile.boundary.RicercaStruttureForm;
import com.example.appmobile.entity.Strutture;

import java.util.ArrayList;
import java.util.List;

public class RicercaStruttureRicettiveController {

    private static RicercaStruttureRicettiveController ricercaStruttureRicettiveController = null;
    private MainFrameForm mainFrameForm;
    private StruttureDao struttureDao;

    //metodo private
    private RicercaStruttureRicettiveController(){

    }

    public static RicercaStruttureRicettiveController getRicercaStruttureRicettiveController(){
        if(ricercaStruttureRicettiveController == null){
            return new RicercaStruttureRicettiveController();
        }
        return ricercaStruttureRicettiveController;
    }


    public void mostraRicercaStrutture(Context context){
        context.startActivity(new Intent(context, RicercaStruttureForm.class));
    }

    public void cercaStrutture(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String maxPrezzo, Context context){

        String service = context.getString(R.string.cloudService);

        StruttureDao struttureDao = DaoFactory.getStruttureDao(service,context);

        List<Strutture> listaStrutture = struttureDao.getStruttureByFiltri(nome,città,valutazioneMedia,distanzaDaDispositivo,orarioApertura,categoria,maxPrezzo);


        //System.out.println("In teoria la query è stata fatta.");
        List<String> listaNomi = new ArrayList<String>();
        List<String> listaCittà = new ArrayList<String>();
        List<String> listaOrariApertura = new ArrayList<String>();
        List<String> listaRangePrezzo = new ArrayList<String>();
        List<Float> listaValutazioni = new ArrayList<Float>();
        List<String> listaLatitudini = new ArrayList<String>();
        List<String> listaLogitudini = new ArrayList<String>();

        Location currentLocation = MainFrameForm.getCurrentLocation();
        for(Strutture s:listaStrutture){
            String latitudine = s.getLatitudine();
            String longitudine = s.getLongitudine();
            float distance = 0.0f;

            if(currentLocation != null){
                /*Calcolando distanza tra dispositivo e struttura corrente*/
                Location markerLocation = new Location("");
                markerLocation.setLatitude(Double.parseDouble(latitudine));
                markerLocation.setLongitude(Double.parseDouble(longitudine));
                distance = currentLocation.distanceTo(markerLocation);
            }
            /*Se il GPS è disattivo la distanza sarà a 0 => tutte le strutture recuperate verranno visualizzate*/
            if(distance <= distanzaDaDispositivo){
                listaNomi.add(s.getNome());
                listaCittà.add(s.getCittà());
                listaOrariApertura.add(s.getOrarioApertura());
                listaRangePrezzo.add(s.getMaxPrezzo());
                listaLatitudini.add(latitudine);
                listaLogitudini.add(longitudine);
                listaValutazioni.add(s.getValutazioneMedia());
            }
        }

        MainFrameForm.aggiornaMappa(listaNomi,listaLatitudini,listaLogitudini,listaCittà,listaValutazioni,listaOrariApertura,listaRangePrezzo);
    }


}
