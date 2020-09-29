package com.example.proveedortulape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.proveedortulape.View.Fragments.Home;
import com.example.proveedortulape.View.Fragments.Products;
import com.example.proveedortulape.View.Fragments.Services;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private TextView nombre_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        nombre_usuario = (TextView)findViewById(R.id.nombre_usuario);
        SharedPreferences preferences= getSharedPreferences("preferencialogin", Context.MODE_PRIVATE);
        nombre_usuario.setText(preferences.getString("usuario",null));
        BottomNavigationView bottomBar =findViewById(R.id.bottombar_nav);
        bottomBar.setOnNavigationItemSelectedListener(navListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_fragments,new Services()).commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){

                        case R.id.tab_home:
                            selectedFragment = new Home();
                            break;
                        case R.id.tab_drivers:
                            selectedFragment = new Products();
                            break;
                        case R.id.tab_services:
                            selectedFragment = new Services();
                            break;
                        default: selectedFragment = new Home();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments,
                            selectedFragment).addToBackStack(null).commit();
                    return true;
                }
            };

}



