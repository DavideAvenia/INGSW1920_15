package com.example.appmobile.boundary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.ScriviRecensioniController;

import java.io.File;
import java.io.IOException;

public class ScriviRecensioniForm extends AppCompatActivity {

    private ImageButton inserisciMedia;
    private Button inviaRecensione;
    private RatingBar recensioneRatingBar;
    private EditText testoRecensione;
    private ScriviRecensioniController scriviRecensioniController;
    private File immagine;

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
            scriviRecensioniController.inserisciRecensione(getApplicationContext(), testoRecensione.getText().toString(), new Float(recensioneRatingBar.getRating()), immagine);
        });
    }

    public void apriGalleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"),1234);

<<<<<<< HEAD
        startActivityForResult(intent, 1234);
=======
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Seleziona un immagine");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, 1);
>>>>>>> 56db97caf49dceab8d0b1e5d2410d50a47dc446f
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "Errore: i dati sono nulli.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                immagine = scriviRecensioniController.getImmagineFromInput(getApplicationContext(), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}