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
            if (!checkEmail(email) || !checkPassword(password) || !checkUsername(userId)) {
                showToast("Email e/o password non validi! Riprovare");
            } else {
                cellulare = prefissiRegistrazione.getSelectedItem().toString() + cellulare;

                controllerLogin.registrazione(userId, nome, cognome, cellulare, email, password, this);
            }
        }

    }

    public boolean checkEmail(String email) {

        String tokens[] = email.split("_");
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || tokens[0].equals("admin")) {
            return false;
        }
        return true;
    }

    public boolean checkUsername(String username) {
        String tokens[] = username.split("_");
        if (tokens[0].equals("admin")) {
            return false;
        }
        return true;
    }

    public boolean checkPassword(String password) {
        final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$");

        if (PASSWORD_PATTERN.matcher(password).matches()) {
            return true;
        }
        return false;
    }

    public void showToast(String messaggio) {
        Toast.makeText(this, messaggio, Toast.LENGTH_LONG).show();
    }


}
