package com.example.proyectominimarket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Perfilfragmento extends Fragment {
    TextView nombre,fechanacimiento,correo;
    View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nombre=v.findViewById(R.id.txvNombre);
        fechanacimiento=v.findViewById(R.id.txvCorreo);
        correo=v.findViewById(R.id.txvCorreo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfilfragmento, container, false);
    }
}