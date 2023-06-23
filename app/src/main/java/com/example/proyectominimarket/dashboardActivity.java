package com.example.proyectominimarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.proyectominimarket.Db.DBuser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class dashboardActivity extends AppCompatActivity {
    DBuser user;
    FragmentTransaction fragmentTransaction;
    BottomNavigationView navigation;
    TextView titulo;
    Map<Integer,String> buttonMap;
    Map<Integer,Fragment>fragmentMap;
    Map<Integer,String>TittleMap;
    Stack<Integer> fragmentStack=new Stack<>();
    int Content=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        titulo=(TextView) findViewById(R.id.txvTitulo);
        navigation=findViewById(R.id.navigationBottom);
        buttonMap=new HashMap<>();
        fragmentMap=new HashMap<>();
        TittleMap=new HashMap<>();
        buttonMap.put(R.id.btnBebidas,"bebidas");
        buttonMap.put(R.id.btnFrutas,"frutas");
        buttonMap.put(R.id.btnVegetales,"vegetales");
        buttonMap.put(R.id.btnAlmacen,"almacen");
        buttonMap.put(R.id.btnDomesticos,"domesticos");
        fragmentMap.put(R.id.home,new Homefragmento());
        fragmentMap.put(R.id.cart,new Cartfragmento());
        fragmentMap.put(R.id.search,new Searchfragmento());
        fragmentMap.put(R.id.profile,new Perfilfragmento());
        TittleMap.put(R.id.home,"Categorias");
        TittleMap.put(R.id.cart,"Carrito");
        TittleMap.put(R.id.search,"Busqueda");
        TittleMap.put(R.id.profile,"Perfil");
        user=new DBuser(this);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment Fragmentselect=fragmentMap.get(item.getItemId());
                String Tittleselect=TittleMap.get(item.getItemId());
                if(Fragmentselect!=null){
                    titulo.setText(Tittleselect);
                    fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainerView,Fragmentselect);
                    fragmentStack.push(item.getItemId());
                    fragmentTransaction.addToBackStack(null).commit();
                    return true;
                }
                return false;
            }
        });
    }

    //public Fragment getfragment(){
    //}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DialogInterface dialogInterface = null;
        if(!fragmentStack.empty()){
            fragmentStack.pop();
        }
        if(fragmentStack.size()>0){
            Integer name=fragmentStack.peek();
            System.out.println(Arrays.asList(fragmentStack));
            navigation.getMenu().findItem(name).setChecked(true);
            titulo.setText(TittleMap.get(name));
            Toast.makeText(this, ""+fragmentStack.size(), Toast.LENGTH_SHORT).show();
        }else{
            titulo.setText(TittleMap.get(R.id.home));
            navigation.getMenu().findItem(R.id.home).setChecked(true);
        }
    }

    public void verproductos(View view){
        String button=buttonMap.get(view.getId());
        if(button!=null){
            Intent viewproducto=new Intent(this,ProductActivity.class);
            viewproducto.putExtra("tipo",button);
            startActivity(viewproducto);
        }
    }
}