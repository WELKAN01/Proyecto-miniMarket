package com.example.proyectominimarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private static final String SHARED_PREF="myaccount";
    private static final String CORREO_LOG="Correo";
    private static final String PASSWORD_LOG="Pass";
    SharedPreferences sharedPreferences;
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
        sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        String correo=sharedPreferences.getString(CORREO_LOG,null);
        String Password=sharedPreferences.getString(PASSWORD_LOG,null);

        Button back=findViewById(R.id.btndashboardback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
        if(!fragmentStack.empty()){
            fragmentStack.pop();
        }
        if(fragmentStack.size()>0){
            Integer name=fragmentStack.peek();
            System.out.println(Arrays.asList(fragmentStack));
            navigation.getMenu().findItem(name).setChecked(true);
            titulo.setText(TittleMap.get(name));
            super.onBackPressed();
        }else{
            navigation.getMenu().findItem(R.id.home).setChecked(true);
            titulo.setText(TittleMap.get(R.id.home));
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView,new Homefragmento()).commit();
            Content++;
            if(Content==1){
                Toast.makeText(this, "Presione una vez mas para salir", Toast.LENGTH_SHORT).
                        show();
            }
            if(Content==2){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                startActivity(new Intent(this,MainActivity.class));
            }
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