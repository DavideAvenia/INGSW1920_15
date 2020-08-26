package com.example.appmobile.boundary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.GestioneProfiloController;

import java.util.regex.Pattern;

public class GestioneProfiloForm extends AppCompatActivity {
    private TextView labelProfilotext;
    private CheckBox checkBox;
    private EditText nicknameText;
    private TextView nomeText;
    private TextView numCell;
    private TextView email;
    private EditText nuovoCellText;
    private Button cambiaCell;
    private EditText nuovaMailText;
    private Button cambiaMail;
    private EditText nuovaPasswordText;
    private EditText nuovaPassword2Text;
    private Button cambiaPsw;
    private TextView aggiornaPasswordLabel;
    private Button logout;
    private GestioneProfiloController gestioneProfiloController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_profilo_form);
        labelProfilotext = findViewById(R.id.labelProfilotext);
        checkBox = findViewById(R.id.checkBox);
        nicknameText = findViewById(R.id.nicknametext);
        nomeText = findViewById(R.id.nomeText);
        nuovoCellText = findViewById(R.id.nuovoCelltext);
        cambiaCell = findViewById(R.id.cambiacell);
        nuovaPasswordText = findViewById(R.id.nuovaPasswordtext);
        nuovaPassword2Text = findViewById(R.id.nuovapassword2text);
        cambiaPsw = findViewById(R.id.cambiapsw);
        nuovaMailText = findViewById(R.id.nuovaMailtext);
        cambiaMail = findViewById(R.id.cambiamail);
        numCell = findViewById(R.id.numCell);
        email = findViewById(R.id.email);


        cambiaCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String nuovoCell = nuovoCellText.getText().toString();
              if(nuovoCell == null){
                  Toast.makeText(GestioneProfiloForm.this, "Non hai inserito un numero valido...",Toast.LENGTH_LONG).show();
              }
                //Effettua le modifiche in cognito
            }
        });
        cambiaMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nuovaMail = nuovaMailText.getText().toString();
                if(nuovaMail == null){
                    Toast.makeText(GestioneProfiloForm.this,"Non hai inserito una Mail valida...",Toast.LENGTH_LONG).show();
                }
            }
        });
        cambiaPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nuovaPSW = nuovaPasswordText.getText().toString();
               String confermaPSW = nuovaPassword2Text.getText().toString();
               if(nuovaPSW.equals(confermaPSW)){
                   if(checkPassword(nuovaPSW)){
                       //AWS cognito tramite controller
                   }
                   Toast.makeText(GestioneProfiloForm.this,"La password deve avere almeno un numero, un minuscolo, un maiuscolo \n" +
                           "un carattere speciale, niente spazi ed almeno 4 caratteri",Toast.LENGTH_LONG).show();
               }
               Toast.makeText(GestioneProfiloForm.this,"Le password non coincidono!!!",Toast.LENGTH_LONG).show();
            }
        });

        nomeText.setText(trovaNomeUtente());
        numCell.setText(trovaCellUtente());
        email.setText(trovaEmailUtente());
    }

    private String trovaEmailUtente() {
        // Recuperare tramite variabile Utente.campodesiderato in controller.
        return null;
    }

    private int trovaCellUtente() {
        int numCell = 0;
        // Recuperare tramite variabile Utente.campodesiderato in controller.
        return numCell;
    }

    public String trovaNomeUtente(){
        gestioneProfiloController = GestioneProfiloController.getGestioneProfiloController();
        // Recuperare tramite variabile Utente.campodesiderato in controller.
        return null;
    }

    public boolean checkPassword(String password){
        final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 4 characters
                "$");

        if(PASSWORD_PATTERN.matcher(password).matches()){
            return true;
        }return false;
    }
}