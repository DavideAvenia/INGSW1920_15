package com.example.appmobile.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobile.Adapters.RecyclerViewFotoRecensioniAdapter;
import com.example.appmobile.Adapters.RecyclerViewRecensioniAdapter;
import com.example.appmobile.R;
import com.example.appmobile.controller.LeggereRecensioniController;
import com.example.appmobile.controller.ScriviRecensioniController;

import java.util.List;

public class RecensioniStruttureForm extends AppCompatActivity {

    private static RecyclerViewRecensioniAdapter recensioniAdapter = null;
    private TextView nomeStrutturaLeggereRecensioni;
    private TextView valutazioneLeggereRecensioniText;
    private TextView descrizioneText;
    private TextView scrivereRecensioniLink;
    private RecyclerView recyclerViewFotoRecensioni;
    private ScrollView scrollViewDescrizioneLeggereRecensione;
    private RecyclerView recyclerViewRecensioni;
    private RatingBar valutazioneLeggereRecensioni;
    private LeggereRecensioniController leggereRecensioniController = null;

    //Struttura corrente
    private String latitudine;
    private String longitudine;
    private String nomeStruttura;

    public static void updateAdapter(String nameToShow, int position) {
        /*In attesa passiva, il thread aspetta che si crei l'adapter prima di aggiornarlo*/
        while (recensioniAdapter == null) {
            System.out.println("L'adapter deve ancora essere inizializzato!");
            try {
                Thread.currentThread().wait(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        recensioniAdapter.setNameToShow(nameToShow, position);
        recensioniAdapter.notifyItemChanged(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensioni_strutture_form);

        leggereRecensioniController = LeggereRecensioniController.getLeggereRecensioniController();

        //Inizializzazione widget
        nomeStrutturaLeggereRecensioni = findViewById(R.id.nomeStrutturaLeggereRecensioni);
        valutazioneLeggereRecensioniText = findViewById(R.id.valutazioneLeggereRecensioniText);
        descrizioneText = findViewById(R.id.descrizioneText);
        scrivereRecensioniLink = findViewById(R.id.scrivereRecensioneLink);
        recyclerViewRecensioni = findViewById(R.id.recyclerViewRecensioni);
        recyclerViewFotoRecensioni = findViewById(R.id.recyclerViewFotoRecensioni);
        valutazioneLeggereRecensioni = findViewById(R.id.valutazioneLeggereRecensioni);
        scrollViewDescrizioneLeggereRecensione = findViewById(R.id.scrollViewDescrizioneLeggereRecensioni);

        /*********************INIZIALIZZAZIONE WIDGET**************************/
        Intent i = getIntent();

        /*Recupero informazioni struttura da intent*/
        latitudine = i.getStringExtra("latitudine");
        longitudine = i.getStringExtra("longitudine");
        nomeStruttura = i.getStringExtra("nomeStruttura");

        List<String> listaTestiRecensioni = i.getStringArrayListExtra("listaTestiRecensioni");
        List<String> listaUrlFoto = i.getStringArrayListExtra("listaUrlFoto");
        List<String> nomiRecensori = i.getStringArrayListExtra("nomiRecensori");
        String descrizione = i.getStringExtra("descrizione");
        float valutazione = i.getFloatExtra("valutazione", 0.0f);
        float listaValutazioni[] = i.getFloatArrayExtra("listaValutazioni");

        valutazioneLeggereRecensioni.setRating(valutazione);

        /*Inserimento informazioni struttura nei widget*/
        nomeStrutturaLeggereRecensioni.setText(nomeStruttura);

        TextView descrizioneStruttura = new TextView(this);
        descrizioneStruttura.setText(descrizione);
        scrollViewDescrizioneLeggereRecensione.addView(descrizioneStruttura);

        /*Inserimento recensioni*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewRecensioni.setLayoutManager(linearLayoutManager);
        recensioniAdapter = new RecyclerViewRecensioniAdapter(this, nomiRecensori, listaTestiRecensioni, listaValutazioni);
        recyclerViewRecensioni.setAdapter(recensioniAdapter);

        /*Inserimento foto*/
        LinearLayoutManager fotoManager = new LinearLayoutManager(this);
        fotoManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewFotoRecensioni.setLayoutManager(fotoManager);
        RecyclerViewFotoRecensioniAdapter fotoAdapter = new RecyclerViewFotoRecensioniAdapter(this, listaUrlFoto);
        recyclerViewFotoRecensioni.setAdapter(fotoAdapter);

        Intent intent = getIntent();

        //Click su scrivere recensione
        scrivereRecensioniLink.setOnClickListener(view -> {
            ScriviRecensioniController controller = ScriviRecensioniController.getScriviRecensioniController();
            controller.mostraScrivereRecensioni(this, nomeStruttura, latitudine, longitudine, intent.getStringExtra("userIdLogged"));

        });

    }


}
