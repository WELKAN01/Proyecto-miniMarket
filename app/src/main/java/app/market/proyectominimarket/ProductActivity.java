package app.market.proyectominimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.market.proyectominimarket.Db.DBuser;

import app.market.proyectominimarket.R;

import app.market.proyectominimarket.adapter.adapterproductos;
import app.market.proyectominimarket.model.producto;

import java.util.ArrayList;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ProductActivity.this,dashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}