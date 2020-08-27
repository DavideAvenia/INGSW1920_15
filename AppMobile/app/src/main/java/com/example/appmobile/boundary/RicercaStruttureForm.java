package com.example.appmobile.boundary;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.RicercaStruttureRicettiveController;

public class RicercaStruttureForm extends AppCompatActivity {

    private RicercaStruttureRicettiveController ricercaStruttureRicettiveController;

    private EditText nomeStrutturaRicerca;
    private Spinner cittàRicerca;
    private Spinner distanzaDaDispositivoRicerca;
    private Spinner orarioAperturaRicerca;
    private Spinner rangePrezzoRicerca;
    private Spinner categoriaRicerca;
    private RatingBar valutazioneMediaRicerca;
    private ImageButton bottoneRicerca;
    private ProgressBar progressBar;
    private TextView valutazioneText;
    private TextView distanzaText;
    private TextView orarioAperturaText;
    private TextView maxPrezzoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_strutture_form);

        nomeStrutturaRicerca = findViewById(R.id.nomeStrutturaRicerca);
        cittàRicerca = findViewById(R.id.cittàRicerca);
        distanzaDaDispositivoRicerca = findViewById(R.id.distanzaDaDispositivoRicerca);
        orarioAperturaRicerca = findViewById(R.id.orarioAperturaRicerca);
        rangePrezzoRicerca = findViewById(R.id.rangePrezzoRicerca);
        categoriaRicerca = findViewById(R.id.categoriaRicerca);
        valutazioneMediaRicerca = findViewById(R.id.valutazioneMediaRicerca);
        bottoneRicerca = findViewById(R.id.bottoneRicerca);
        valutazioneText = findViewById(R.id.valutazioneText);
        distanzaText = findViewById(R.id.distanzaText);
        orarioAperturaText = findViewById(R.id.orarioAperturaText);
        maxPrezzoText = findViewById(R.id.maxPrezzoText);
        progressBar = findViewById(R.id.progressBarRicercaStrutture);

        ricercaStruttureRicettiveController = RicercaStruttureRicettiveController.getRicercaStruttureRicettiveController();

        /**************Inizializzazione spinners***************/
        //spinner città
        ArrayAdapter<CharSequence> spinnerCittà = ArrayAdapter.createFromResource(this, R.array.città, android.R.layout.simple_spinner_item);
        spinnerCittà.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cittàRicerca.setAdapter(spinnerCittà);

        //spinner distanza
        ArrayAdapter<CharSequence> spinnerDistanza = ArrayAdapter.createFromResource(this, R.array.distanzaDispositivo, android.R.layout.simple_spinner_item);
        spinnerDistanza.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanzaDaDispositivoRicerca.setAdapter(spinnerDistanza);

        //spinner rangePrezzo
        ArrayAdapter<CharSequence> spinnerPrezzo = ArrayAdapter.createFromResource(this, R.array.maxPrezzo, android.R.layout.simple_spinner_item);
        spinnerPrezzo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangePrezzoRicerca.setAdapter(spinnerPrezzo);

        //spinner categoria
        ArrayAdapter<CharSequence> spinnerCategoria = ArrayAdapter.createFromResource(this, R.array.categoriaStruttura, android.R.layout.simple_spinner_item);
        spinnerCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaRicerca.setAdapter(spinnerCategoria);

        //spinner orarioApertura
        ArrayAdapter<CharSequence> spinnerApertura = ArrayAdapter.createFromResource(this, R.array.orarioApertura, android.R.layout.simple_spinner_item);
        spinnerApertura.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orarioAperturaRicerca.setAdapter(spinnerApertura);

        /*******************************************************/

        //Inizializzazione RatingBar
        valutazioneMediaRicerca.setNumStars(5);
        valutazioneMediaRicerca.setRating(3);

        bottoneRicerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottoneRicercaPremuto(v);
            }
        });

    }


    public void bottoneRicercaPremuto(View view) {
        String nome = nomeStrutturaRicerca.getText().toString();
        String città = cittàRicerca.getSelectedItem().toString();
        String orarioApertura = orarioAperturaRicerca.getSelectedItem().toString();
        String maxPrezzo = rangePrezzoRicerca.getSelectedItem().toString();
        String categoria = categoriaRicerca.getSelectedItem().toString();
        int distanza = Integer.parseInt(distanzaDaDispositivoRicerca.getSelectedItem().toString());
        float valutazioneMedia = valutazioneMediaRicerca.getRating();

        progressBar.setVisibility(View.VISIBLE);
        ricercaStruttureRicettiveController.cercaStrutture(nome, città, valutazioneMedia, distanza, orarioApertura, categoria, maxPrezzo, this);
        progressBar.setVisibility(View.GONE);
        this.finish();
    }


}
