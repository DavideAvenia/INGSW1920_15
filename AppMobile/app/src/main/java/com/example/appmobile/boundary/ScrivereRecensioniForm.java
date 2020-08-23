package com.example.appmobile.boundary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.ScrivereRecensioniController;

public class ScrivereRecensioniForm extends AppCompatActivity {

    private ImageButton inserisciMedia;
    private Button inviaRecensione;
    private RatingBar recensioneRatingBar;
    private EditText testoRecensione;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrivi_recensioni_form);

        inserisciMedia = findViewById(R.id.inserisciMedia);
        inviaRecensione = findViewById(R.id.inviaRecensione);
        recensioneRatingBar = findViewById(R.id.recensioneRatingBar);
        testoRecensione = findViewById(R.id.testoRecensione);

        inviaRecensione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrivereRecensioniController scrivereRecensioniController = ScrivereRecensioniController.getScrivereRecensioniController();
                //Da aggiungere una ArrayList di media generici
                //AGGIUNGERE I PERMESSI DI ACCESSO AI MEDIA
                scrivereRecensioniController.inserisciRecensione(getApplicationContext(), testoRecensione.getText().toString(), new Float(recensioneRatingBar.getRating()));
            }
        });
    }

}
