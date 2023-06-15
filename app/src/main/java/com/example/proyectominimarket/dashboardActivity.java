package com.example.proyectominimarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class dashboardActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    BottomNavigationView navigation;
    TextView titulo;
    Map<Integer,Fragment>fragmentMap;
    Map<Integer,String>TittleMap;
    Stack<Integer> fragmentStack=new Stack<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        titulo=(TextView) findViewById(R.id.txvTitulo);
        navigation=findViewById(R.id.navigationBottom);
        fragmentMap=new HashMap<>();
        TittleMap=new HashMap<>();
        fragmentMap.put(R.id.home,new Homefragmento());
        fragmentMap.put(R.id.cart,new Cartfragmento());
        fragmentMap.put(R.id.search,new Searchfragmento());
        fragmentMap.put(R.id.profile,new Perfilfragmento());
        TittleMap.put(R.id.home,"Categorias");
        TittleMap.put(R.id.cart,"Carrito");
        TittleMap.put(R.id.search,"Busqueda");
        TittleMap.put(R.id.profile,"Perfil");

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
        MenuItem items;
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
            Toast.makeText(this, "presiona 2 veces para salir", Toast.LENGTH_SHORT).show();
        }

    }
}