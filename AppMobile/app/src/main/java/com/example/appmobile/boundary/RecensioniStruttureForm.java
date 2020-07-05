package com.example.appmobile.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.appmobile.R;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

public class RecensioniStruttureForm extends AppCompatActivity {

    private TextView nomeStrutturaLeggereRecensioni;
    private TextView valutazioneLeggereRecensioniText;
    private TextView descrizioneText;
    private TextView scrivereRecensioniLink;
    private HorizontalScrollView scrollViewFotoDaRecensioni;
    private HorizontalScrollView scrollViewRecensioni;
    private ScrollView scrollViewDescrizioneLeggereRecensione;
    private LinearLayout linearLayoutDescrizioneLeggereRecensione;
    private LinearLayout linearLayoutFotoDaRecensioni;
    private LinearLayout linearLayoutRecensioni;
    private RatingBar valutazioneLeggereRecensioni;

    //Struttura corrente
    private LatLng latLng;
    private String nomeStruttura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensioni_strutture_form);

        //Inizializzazione widget
        nomeStrutturaLeggereRecensioni = findViewById(R.id.nomeStrutturaLeggereRecensioni);
        valutazioneLeggereRecensioniText = findViewById(R.id.valutazioneLeggereRecensioniText);
        descrizioneText = findViewById(R.id.descrizioneText);
        scrivereRecensioniLink = findViewById(R.id.scrivereRecensioneLink);
        scrollViewFotoDaRecensioni = findViewById(R.id.scrollViewFotoDaRecensioni);
        scrollViewRecensioni = findViewById(R.id.scrollViewRecensioni);
        linearLayoutFotoDaRecensioni = findViewById(R.id.linearLayoutFotoDaRecensioni);
        linearLayoutRecensioni = findViewById(R.id.linearLayoutRecensioni);
        valutazioneLeggereRecensioni = findViewById(R.id.valutazioneLeggereRecensioni);
        scrollViewDescrizioneLeggereRecensione = findViewById(R.id.scrollViewDescrizioneLeggereRecensioni);
        linearLayoutDescrizioneLeggereRecensione = findViewById(R.id.linearLayoutDescrizioneLeggereRecensioni);

        Intent i = getIntent();

        latLng = new LatLng(i.getDoubleExtra("latitudine",0.0),i.getDoubleExtra("longitudine",0.0));
        nomeStruttura = i.getStringExtra("nomeStruttura");
        nomeStrutturaLeggereRecensioni.setText(nomeStruttura);

    }

}
