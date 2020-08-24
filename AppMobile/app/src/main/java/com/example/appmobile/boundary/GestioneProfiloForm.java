package com.example.appmobile.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appmobile.R;

public class GestioneProfiloForm extends AppCompatActivity {
    private TextView labelProfilotext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_profilo_form);

        labelProfilotext = findViewById(R.id.labelProfilotext);

    }
}