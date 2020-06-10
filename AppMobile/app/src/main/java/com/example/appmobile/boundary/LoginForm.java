package com.example.appmobile.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appmobile.R;
import com.example.appmobile.controller.ControllerLogin;

public class LoginForm extends AppCompatActivity {

    private ControllerLogin controllerLogin;
    private EditText userId;
    private EditText passwordLogin;
    private TextView registratiLogin;
    private TextView recuperaPasswordLogin;
    private Button bottoneLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        controllerLogin = ControllerLogin.getControllerLogin();

        userId = findViewById(R.id.userId);
        passwordLogin = findViewById(R.id.passwordLogin);
        registratiLogin = findViewById(R.id.registratiLogin);
        bottoneLogin = findViewById(R.id.bottoneLogin);
        recuperaPasswordLogin = findViewById(R.id.recuperaPasswordLogin);
    }

    public void bottoneLoginPremuto(View view){
        String userIds = userId.getText().toString();
        String password = passwordLogin.getText().toString();

        controllerLogin.login(userIds,password,this);
    }

    public void registratiPremuto(View view){
        controllerLogin.mostraRegistrazioneForm(this);
    }

    public void recuperaPasswordPremuto(View view){

    }

}
