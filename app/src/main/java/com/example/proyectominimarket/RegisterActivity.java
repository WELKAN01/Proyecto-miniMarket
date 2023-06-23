package com.example.proyectominimarket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectominimarket.Db.DBuser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    private DBuser dBuser;
    EditText fecha,correo,nombre,password,confirmar;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        fecha=findViewById(R.id.edtdate);
        correo=findViewById(R.id.edtCorreo);
        nombre=findViewById(R.id.edtname);
        password=findViewById(R.id.edtcontra);
        confirmar=findViewById(R.id.edtconfimarcontra);
        btnback=findViewById(R.id.btnback);
        dBuser=new DBuser(this);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarfechas();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void Registro(View view) throws ParseException {
        DateFormat dt=new SimpleDateFormat("dd/mm/yyyy");
        DateFormat dt2=new SimpleDateFormat("mm-dd-yyyy");
        String nombreData=nombre.getText().toString();
        String correoData=correo.getText().toString();
        String passworData=password.getText().toString();
        String confirmardata= confirmar.getText().toString();
        Date date=dt.parse(fecha.getText().toString());
        String date2=dt2.format(date);
        if(passworData.trim().equalsIgnoreCase(confirmardata.trim())){
            boolean respuesta=dBuser.insertarDatos(nombreData,correoData,date2,passworData);
            if(respuesta){
                Toast.makeText(this, "Felicidades por registrarte", Toast.LENGTH_LONG).
                        show();
                onBackPressed();
            }
        }else{
            Toast.makeText(this, "no hay coincidencia en las contraseñas", Toast.LENGTH_LONG).
                    show();
        }
    }
    public void mostrarfechas(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Actualizar el texto del EditText con la fecha seleccionada
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        fecha.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}