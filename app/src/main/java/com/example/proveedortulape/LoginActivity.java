package com.example.proveedortulape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
     EditText edt_usuario,edt_clave;
     Button btn_iniciar;
     String usuario, clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_usuario = findViewById(R.id.user);

        edt_clave = findViewById(R.id.password);
        btn_iniciar = findViewById(R.id.btn_login);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = edt_usuario.getText().toString();
                clave = edt_clave.getText().toString();
                if(!usuario.isEmpty()&&!clave.isEmpty()){
                    validarUsuario("https://tulape.herokuapp.com/android/login.php");
                }else{
                    Toast.makeText(LoginActivity.this,"Debes completar tus datos.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void validarUsuario(String Url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    guardarPreferencia();
                    Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Usuario o contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("usuario",usuario);
                parametros.put("clave",clave);
                return parametros;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    //Añadimos las preferencias pafra guardar datos del login
    private void guardarPreferencia(){
        //utilizamos PreferenciaLogin que sirve como referencia para recuperar los datos
        SharedPreferences preferences= getSharedPreferences("preferencialogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("clave",clave);
        editor.putBoolean("sesion",true);
        editor.commit();
    }



}