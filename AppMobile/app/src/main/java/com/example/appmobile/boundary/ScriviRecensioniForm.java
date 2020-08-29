package com.example.appmobile.boundary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.ScriviRecensioniController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.android.gms.common.util.IOUtils.copyStream;

public class ScriviRecensioniForm extends AppCompatActivity {

    private ImageButton inserisciMedia;
    private Button inviaRecensione;
    private RatingBar recensioneRatingBar;
    private EditText testoRecensione;
    private ScriviRecensioniController scriviRecensioniController;
    private File immagine = null;

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
            finish();
        });
    }

    public void apriGalleria() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleziona un immagine"), 1234);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            try {
                // Creating file
                immagine = null;
                try {
                    immagine = createImageFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                FileOutputStream fileOutputStream = new FileOutputStream(immagine);
                // Copying
                ScriviRecensioniController.copyStream(inputStream, fileOutputStream);
                fileOutputStream.close();
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private  File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}