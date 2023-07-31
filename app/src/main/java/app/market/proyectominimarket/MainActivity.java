package app.market.proyectominimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.market.proyectominimarket.Db.DBuser;

import app.market.proyectominimarket.R;

import app.market.proyectominimarket.model.usuario;

public class MainActivity extends AppCompatActivity {
    DBuser user;
    Button btningresar,visibility;
    boolean Visible=true;
    TextView bienvenido,btnRegistro;
    EditText edEmail,edPassword;
    private static final String SHARED_PREF="myaccount";
    private static final String CORREO_LOG="Correo";
    private static final String PASSWORD_LOG="Pass";
    boolean i=true;
    SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //l=findViewById(androidx.constraintlayout.widget.R.id.constraint);
        btningresar=findViewById(R.id.btnIngreso);
        btnRegistro=findViewById(R.id.txvRegistro);
        bienvenido=findViewById(R.id.txvBienvenido);
        visibility=findViewById(R.id.btnhide);
        edEmail=findViewById(R.id.edtEmail);
        edPassword=findViewById(R.id.edtPassword);
        ConstraintLayout constra=(ConstraintLayout) findViewById(R.id.constraintLayout);
        ConstraintLayout constralogin=(ConstraintLayout) findViewById(R.id.constraintlogin);
        AnimationDrawable animationDrawable=(AnimationDrawable) constra.getBackground();
        sharedPreferences=getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        user=new DBuser(this);
        if(sharedPreferences.contains(CORREO_LOG)){
            String correo=sharedPreferences.getString(CORREO_LOG,null);
            Intent intent=new Intent(MainActivity.this,dashboardActivity.class);
            Toast.makeText(MainActivity.this, "BIENVENIDO "+correo, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else {
            btnRegistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });
            btningresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Email, Password;
                    Email = edEmail.getText().toString();
                    Password = edPassword.getText().toString();
                    usuario u = new usuario(null, Email, Password, null);

                    if (i) {
                        bienvenido.setVisibility(v.INVISIBLE);
                        animationDrawable.setOneShot(true);
                        animationDrawable.setEnterFadeDuration(1000);
                        animationDrawable.start();
                        try {
                            Thread.sleep(1000);
                            constralogin.setVisibility(v.VISIBLE);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        i = false;
                    } else {
                        if (!Email.isEmpty() && !Password.isEmpty()) {
                            boolean respuesta = user.verificarusuarioyContra(u.getCorreo(), u.getPassword());
                            if (respuesta) {
                                editor.putString(CORREO_LOG, Email);
                                editor.putString(PASSWORD_LOG, Password);
                                editor.apply();
                                Intent intent = new Intent(MainActivity.this, dashboardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "BIENVENIDO "+sharedPreferences.getString(CORREO_LOG,null), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "no existe esa cuenta",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Ingrese el email u contraseña faltante",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            visibility.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    if (Visible) {
                        edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        v.setBackgroundResource(R.drawable.baseline_visibility_24);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            v.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        }
                        Visible = false;
                    } else {
                        edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                        v.setBackgroundResource(R.drawable.baseline_visibility_off_24);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            v.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(124, 124, 124)));
                        }
                        Visible = true;
                    }
                }
            });
        }
    }

}