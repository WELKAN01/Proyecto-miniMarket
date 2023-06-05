package com.example.proyectominimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btningresar;
    TextView bienvenido;
    ConstraintLayout l;
    boolean i=true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //l=findViewById(androidx.constraintlayout.widget.R.id.constraint);
        btningresar=findViewById(R.id.btnIngreso);
        bienvenido=findViewById(R.id.txvBienvenido);
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i){
                    bienvenido.setVisibility(v.INVISIBLE);
                    i=false;
                }else{
                    bienvenido.setVisibility(v.VISIBLE);
                    i=true;
                }

            }
        });
    }

}