package com.example.proveedortulape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class LoadActivity extends AppCompatActivity {
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //creamos una tarea que se ejecute luego de 2 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("preferencialogin",Context.MODE_PRIVATE);
                boolean sesion = preferences.getBoolean("sesion",false);
                if(sesion){
                    Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}