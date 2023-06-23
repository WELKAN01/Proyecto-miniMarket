package com.example.proyectominimarket;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectominimarket.Db.DBuser;
import com.example.proyectominimarket.model.usuario;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Perfilfragmento extends Fragment {
    TextView nombre,fechanacimiento,correo;

    private DBuser user;
    usuario u;
    public Perfilfragmento() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfilfragmento, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String item=getActivity().getIntent().getExtras().getString("correo");
        System.out.println(item);
        nombre=view.findViewById(R.id.txvNombreperfil);
        fechanacimiento=view.findViewById(R.id.txvEdadperfil);
        correo=view.findViewById(R.id.txvCorreoperfil);
        user=new DBuser(getContext());
        usuario date= user.verificarusuario(item);
        LocalDate fecha;
        if(date!=null){
            DateTimeFormatter sd= DateTimeFormatter.ofPattern("MM-dd-yyyy");
            fecha=LocalDate.parse(date.getNacimiento(),sd);
            Period valor=fecha.until(LocalDate.now());
            int year=valor.getYears();
            nombre.setText(date.getNombre());
            fechanacimiento.setText(year+" años");
            correo.setText(date.getCorreo());
        }else{
            Toast.makeText(getContext(), "no existe ", Toast.LENGTH_SHORT).show();
        }

    }
}