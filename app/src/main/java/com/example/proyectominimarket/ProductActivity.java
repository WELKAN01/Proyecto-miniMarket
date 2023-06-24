package com.example.proyectominimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.proyectominimarket.Db.DBuser;
import com.example.proyectominimarket.adapter.adapterproductos;
import com.example.proyectominimarket.model.producto;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    RecyclerView listproducto;
    List<producto> listaproductoArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().hide();
        String content=getIntent().getStringExtra("tipo");
        listproducto=findViewById(R.id.listproductoactivity);
        listproducto.setLayoutManager(new LinearLayoutManager(this));
        DBuser db=new DBuser(this);
        Toast.makeText(this,db.obtenerproducto(content).size()+"", Toast.LENGTH_SHORT).show();
        listaproductoArray=new ArrayList<>();
        adapterproductos Adapterproductos=new adapterproductos((ArrayList<producto>) db.obtenerproducto(content));
        listproducto.setAdapter(Adapterproductos);

    }
}