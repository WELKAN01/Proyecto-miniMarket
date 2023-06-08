package com.example.proyectominimarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class dashboardActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    Fragment principalfragment,searchfragment,carritofragment,perfilfragment;
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        principalfragment=new Homefragmento();
        carritofragment=new Cartfragmento();
        searchfragment=new Searchfragmento();
        perfilfragment=new Perfilfragmento();
        setContentView(R.layout.activity_dashboard);
        navigation=findViewById(R.id.navigationBottom);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView,principalfragment);
                        fragmentTransaction.addToBackStack(null).commit();
                        return true;
                    case R.id.search:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView,searchfragment);
                        fragmentTransaction.addToBackStack(null).commit();
                        return true;
                    case R.id.cart:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView,carritofragment);
                        fragmentTransaction.addToBackStack(null).commit();
                        return true;
                    case R.id.profile:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView,perfilfragment);
                        fragmentTransaction.addToBackStack(null).commit();
                        return true;
                }
                return false;
            }
        });
    }
}