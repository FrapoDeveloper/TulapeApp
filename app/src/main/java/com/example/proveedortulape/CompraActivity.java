package com.example.proveedortulape;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class CompraActivity extends AppCompatActivity {
    EditText name_user,name_product, present_product,number_product,type_pay;
    Button btn_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        name_user = (EditText) findViewById(R.id.name_user);
        name_product = (EditText) findViewById(R.id.name_product);
        present_product = (EditText)findViewById(R.id.present_product);
        number_product = (EditText)findViewById(R.id.number_product);
        type_pay = (EditText)findViewById(R.id.type_pay);
        btn_buy = (Button)findViewById(R.id.btn_buy);

        //Recuperamos prefencias del usuario y del producto
        SharedPreferences preferences= getSharedPreferences("preferencialogin", Context.MODE_PRIVATE);
        name_user.setText(preferences.getString("usuario",null));
        recuperarPreferencias();

        //Creamos una toolbar para titulo e ir al manifest para indicar el padre de regreso
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Compra");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Agregamos un slider
        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://scontent.ftru2-3.fna.fbcdn.net/v/t1.0-9/51669932_2569786043298817_5190834960696606720_o.jpg?_nc_cat=106&_nc_sid=e3f864&_nc_ohc=tlQuUeQo8fMAX8HUTl1&_nc_ht=scontent.ftru2-3.fna&oh=ca69322a7f5f3c5c5f5688b3b0ba4065&oe=5F92390D",""));
        slideModels.add(new SlideModel("https://scontent.ftru2-3.fna.fbcdn.net/v/t31.0-8/23632468_2164444040499688_174524775323375560_o.jpg?_nc_cat=108&_nc_sid=e3f864&_nc_ohc=TKyQWgVhokgAX8hpKbb&_nc_ht=scontent.ftru2-3.fna&oh=b0c8b5213d539dd07f44c99e260835c6&oe=5F9510E9",""));
        slideModels.add(new SlideModel("https://scontent.ftru2-1.fna.fbcdn.net/v/t1.0-9/38011823_2392885627655527_4932548697100648448_o.jpg?_nc_cat=104&_nc_sid=e3f864&_nc_ohc=BAB8yO6FTq8AX_CkC0U&_nc_ht=scontent.ftru2-1.fna&oh=7b7e876f422262784df93e9471fcb30f&oe=5F959420",""));
        imageSlider.setImageList(slideModels,true);


        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ejecutarServicio("https://tulape.herokuapp.com/android/neworder.php");
                Toast.makeText(CompraActivity.this,"Compra realizada correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);

            }
        });
    }

    private void ejecutarServicio(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(CompraActivity.this,"Compra realizada correctamente", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nombre_usuario",name_user.getText().toString());
                parametros.put("nombre_producto",name_product.getText().toString());
                parametros.put("presentacion_producto",present_product.getText().toString());
                parametros.put("cantidad_producto",number_product.getText().toString());
                parametros.put("tipo_pago",type_pay.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private  void recuperarPreferencias(){
        //recuperamos los datos llamando al nombre de referencia
        SharedPreferences preferences  =getSharedPreferences("preferencialogin", Context.MODE_PRIVATE);
        name_product.setText(preferences.getString("nombre_producto", "Cerveza Tulape"));
        present_product.setText(preferences.getString("presentacion_producto", "Chica"));
    }

}