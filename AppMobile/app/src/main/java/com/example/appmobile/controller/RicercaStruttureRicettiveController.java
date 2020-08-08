package com.example.appmobile.controller;

import android.content.Context;
import android.content.Intent;

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

        System.out.println("Siamo nel controller dopo aver cliccato cerca");
        StruttureDao struttureDao = DaoFactory.getStruttureDao(service,context);

        List<Strutture> listaStrutture = struttureDao.getStruttureByFiltri(nome,città,valutazioneMedia,distanzaDaDispositivo,orarioApertura,categoria,maxPrezzo);


        //System.out.println("In teoria la query è stata fatta.");
        List<String> listaNomi = new ArrayList<String>();
        List<String> listaCittà = new ArrayList<String>();
        List<String> listaOrariApertura = new ArrayList<String>();
        List<String> listaRangePrezzo = new ArrayList<String>();
        List<Float> listaValutazioni = new ArrayList<Float>();
        List<Float> listaLatitudini = new ArrayList<Float>();
        List<Float> listaLogitudini = new ArrayList<Float>();

        for(Strutture s:listaStrutture){
            listaNomi.add(s.getNome());
            listaCittà.add(s.getCittà());
            listaOrariApertura.add(s.getOrarioApertura());
            listaRangePrezzo.add(s.getMaxPrezzo());
            listaLatitudini.add(s.getLatitudine());
            listaLogitudini.add(s.getLongitudine());
            listaValutazioni.add(s.getValutazioneMedia());
        }

        MainFrameForm.aggiornaMappa(listaNomi,listaLatitudini,listaLogitudini,listaCittà,listaValutazioni,listaOrariApertura,listaRangePrezzo);
    }


}
