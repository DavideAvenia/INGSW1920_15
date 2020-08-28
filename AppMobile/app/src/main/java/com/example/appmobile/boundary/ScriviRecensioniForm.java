package com.example.appmobile.boundary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.ScriviRecensioniController;

public class ScriviRecensioniForm extends AppCompatActivity {

    private ImageButton inserisciMedia;
    private Button inviaRecensione;
    private RatingBar recensioneRatingBar;
    private EditText testoRecensione;
    private ScriviRecensioniController scriviRecensioniController;
    private Uri immagine = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrivi_recensioni_form);

        ScriviRecensioniController scriviRecensioniController = ScriviRecensioniController.getScriviRecensioniController();
        inserisciMedia = findViewById(R.id.inserisciMedia);
        inviaRecensione = findViewById(R.id.inviaRecensione);
        recensioneRatingBar = findViewById(R.id.recensioneRatingBar);
        testoRecensione = findViewById(R.id.testoRecensione);

        inserisciMedia.setOnClickListener(view -> apriGalleria());

        inviaRecensione.setOnClickListener(view -> {
            //DEVE PASSARE UNA SOLA IMMAGINE
            scriviRecensioniController.inserisciRecensione(this, testoRecensione.getText().toString(), new Float(recensioneRatingBar.getRating()), immagine);
        });
    }

    public void apriGalleria() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"),1234);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "Errore: i dati sono nulli.", Toast.LENGTH_SHORT).show();
                return;
            }
            immagine = data.getData();
        }
    }
}