package com.example.appmobile.boundary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.R;
import com.example.appmobile.controller.ControllerLogin;

public class ResetPasswordForm extends AppCompatActivity {

    private ControllerLogin controllerLogin;

    private EditText userIdRecuperPassword;
    private EditText codiceRecuperaPassword;
    private EditText passwordRecuperaPassword;
    private Button bottoneRichiediCodice;
    private Button bottoneResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_form);

        controllerLogin = ControllerLogin.getControllerLogin();

        userIdRecuperPassword = findViewById(R.id.userIdRecuperaPassword);
        codiceRecuperaPassword = findViewById(R.id.codiceRecuperaPassword);
        passwordRecuperaPassword = findViewById(R.id.passwordRecuperaPassword);
        bottoneRichiediCodice = findViewById(R.id.bottoneRichiediCodice);
        bottoneResetPassword = findViewById(R.id.bottoneResetPassword);
    }

    public void richiediCodicePremuto(View view) {
        String userId = userIdRecuperPassword.getText().toString();

        controllerLogin.richiediCodiceResetPassword(this, userId);
    }

    public void resetPasswordPremuto(View view) {
        String code = codiceRecuperaPassword.getText().toString();
        String password = passwordRecuperaPassword.getText().toString();

        controllerLogin.resetPassword(this, code, password);
    }
}
