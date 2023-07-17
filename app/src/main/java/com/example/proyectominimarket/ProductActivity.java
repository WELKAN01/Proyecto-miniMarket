package com.example.proyectominimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectominimarket.Db.DBuser;
import com.example.proyectominimarket.adapter.adapterproductos;
import com.example.proyectominimarket.model.producto;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    RecyclerView listproducto;
    Button button;
    ArrayList<producto> listaproductoArray=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //visualizar los productos de la categoria seleccionada

        setContentView(R.layout.activity_product);
        getSupportActionBar().hide();
        String content=getIntent().getStringExtra("tipo");
        button=findViewById(R.id.btnback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this,dashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        //se realiza la iniciacion del la lista para los productos

        listproducto=findViewById(R.id.listproductoactivity);
        listproducto.setLayoutManager(new LinearLayoutManager(this));
        DBuser db=new DBuser(this);

        //el metodo db.obtenerproducto(content), el content es una variable para buscar mediante
        //el contenido de categoria seleccionado
        if(content.isEmpty()){
            listaproductoArray= (ArrayList<producto>) db.obtenerproductototal();
        }else{
            listaproductoArray= (ArrayList<producto>) db.obtenerproducto(content);
        }


        //se utilizara este adaptador para el reclycerlist
        adapterproductos Adapterproductos=new adapterproductos(listaproductoArray);
        listproducto.setAdapter(Adapterproductos);

    }
}