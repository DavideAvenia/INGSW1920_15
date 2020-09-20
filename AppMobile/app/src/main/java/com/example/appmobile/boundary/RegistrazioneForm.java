package com.example.appmobile.boundary;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.ControllerLogin;

import java.util.regex.Pattern;

public class RegistrazioneForm extends AppCompatActivity {

    private ControllerLogin controllerLogin;

    private EditText userIdReg;
    private EditText nomeResgistrazione;
    private EditText cognomeRegistrazione;
    private EditText cellulareRegistrazione;
    private EditText emailRegistrazione;
    private EditText passwordRegistrazione;
    private Spinner prefissiRegistrazione;
    private Button bottoneRegistrazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_form);

        //Recupero istanza controller
        controllerLogin = ControllerLogin.getControllerLogin();

        //Recupero dei widget
        userIdReg = findViewById(R.id.userIdReg);
        nomeResgistrazione = findViewById(R.id.nomeRegistrazione);
        cognomeRegistrazione = findViewById(R.id.cognomeRegistrazione);
        cellulareRegistrazione = findViewById(R.id.cellulareRegistrazione);
        emailRegistrazione = findViewById(R.id.emailRegistrazione);
        passwordRegistrazione = findViewById(R.id.passwordRegistrazione);
        prefissiRegistrazione = findViewById(R.id.prefissiRegistrazione);
        bottoneRegistrazione = findViewById(R.id.bottoneRegistrazione);

        //Inizializzazione combobox contenenti i prefissi per i cellulari
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.prefissi, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prefissiRegistrazione.setAdapter(spinnerAdapter);


    }

    public void bottoneRegistrazioPremuto(View view) {

        String userId = userIdReg.getText().toString();
        String nome = nomeResgistrazione.getText().toString();
        String cognome = cognomeRegistrazione.getText().toString();
        String cellulare = cellulareRegistrazione.getText().toString();
        String email = emailRegistrazione.getText().toString();
        String password = passwordRegistrazione.getText().toString();

        if (userId.equals("") || nome.equals("") || cognome.equals("") || cellulare.equals("") || email.equals("") || password.equals("")) {
            showToast("Tutti i campi sono obbligatori!");
        } else {
            if (!controllerLogin.checkEmail(email) || !controllerLogin.checkPassword(password) || !controllerLogin.checkUsername(userId)) {
                showToast("Email e/o password non validi! Riprovare");
            } else {
                cellulare = prefissiRegistrazione.getSelectedItem().toString() + cellulare;

                controllerLogin.registrazione(userId, nome, cognome, cellulare, email, password, this);
            }
        }

    }

    public void showToast(String messaggio) {
        Toast.makeText(this, messaggio, Toast.LENGTH_LONG).show();
    }


}
