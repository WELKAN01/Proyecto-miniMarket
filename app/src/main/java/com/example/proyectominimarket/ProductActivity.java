package com.example.proyectominimarket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.proyectominimarket.Db.DBuser;

public class ProductActivity extends AppCompatActivity {
    RecyclerView listproducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().hide();
        String content=getIntent().getExtras().getString("tipo");
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        listproducto=findViewById(R.id.listproductoactivity);
        listproducto.setLayoutManager(new LinearLayoutManager(this));
        DBuser db=new DBuser(this);

    }
}