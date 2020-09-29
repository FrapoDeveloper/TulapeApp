package com.example.proveedortulape.View.Fragments;



import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proveedortulape.CompraActivity;
import com.example.proveedortulape.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        showToolbar("Productos",false,view);

        Button iracompra = (Button) view.findViewById(R.id.ir_compra);
        iracompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CompraActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        //Casstealo a un AppCompatActivity y trae un elemento igual al container
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


}
