package com.example.proyectominimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btningresar;
    TextView bienvenido;
    EditText edEmail,edPassword;
    boolean i=true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //l=findViewById(androidx.constraintlayout.widget.R.id.constraint);
        btningresar=findViewById(R.id.btnIngreso);
        bienvenido=findViewById(R.id.txvBienvenido);
        edEmail=findViewById(R.id.edtEmail);
        edPassword=findViewById(R.id.edtPassword);
        ConstraintLayout constra=(ConstraintLayout) findViewById(R.id.constraintLayout);
        ConstraintLayout constralogin=(ConstraintLayout) findViewById(R.id.constraintlogin);
        AnimationDrawable animationDrawable=(AnimationDrawable) constra.getBackground();

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email,Password;
                Email=edEmail.getText().toString();
                Password=edPassword.getText().toString();
                if(i){
                    bienvenido.setVisibility(v.INVISIBLE);
                    animationDrawable.setOneShot(true);
                    animationDrawable.setEnterFadeDuration(500);
                    animationDrawable.start();
                    try {
                        Thread.sleep(1000);
                        constralogin.setVisibility(v.VISIBLE);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    i=false;
                }else{
                    if(!Email.isEmpty() && !Password.isEmpty()){
                        Intent intent=new Intent(v.getContext(), dashboardActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }

}