package com.example.proyectominimarket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectominimarket.Db.DBuser;
import com.example.proyectominimarket.model.car;

import java.util.ArrayList;

public class Cartfragmento extends Fragment {

    ConstraintLayout vacio,listlayout;
    ArrayList<car> arrraycar=new ArrayList<>();
    LinearLayout list;
    float importepagar = 0;
    DBuser db;
    SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cartfragmento, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listlayout=view.findViewById(R.id.layoutList);
        list=view.findViewById(R.id.Listlinear);
        vacio=view.findViewById(R.id.layoutvacio);
        db=new DBuser(view.getContext());
        sp=view.getContext().getSharedPreferences("myaccount", Context.MODE_PRIVATE);
        String correo=sp.getString("Correo",null);
        Cursor cursor=db.obtenerproductosdelcarrito(correo);
        System.out.println(cursor.getCount());
        if(cursor.getCount()>0){
            vacio.setVisibility(View.GONE);
            listlayout.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()){
                int id=cursor.getInt(0);
                String nombrepro=cursor.getString(1);
                int cantidad=cursor.getInt(2);
                double pagar=cursor.getDouble(3);
                arrraycar.add(new car(id,nombrepro,cantidad,pagar));
                importepagar+=pagar;
            }
            VerproductoenCarrito();
        }else{
            vacio.setVisibility(View.VISIBLE);
            listlayout.setVisibility(View.INVISIBLE);
        }
    }

    private void VerproductoenCarrito() {
        TextView totalpago=getView().findViewById(R.id.txvtotalpagar);
        totalpago.setText("");
        list.removeAllViews();
        for (car carrito:arrraycar){
            View itemview=LayoutInflater.from(getContext()).inflate(R.layout.list_car,list,false);
            ImageView imagen=itemview.findViewById(R.id.ImgProduct);
            Button btndelete=itemview.findViewById(R.id.btnborrar);
            TextView nombre=itemview.findViewById(R.id.tvNombre);
            TextView precio=itemview.findViewById(R.id.tvPrecio);
            int resourceid=
                    itemview.getContext().
                            getResources().
                            getIdentifier(
                                    carrito.getNombre().replace(" ",""),
                                    "drawable",itemview.getContext().getPackageName()
                            );
            if(resourceid!=0){
                Drawable drawable=getContext().getResources().getDrawable(resourceid);
                imagen.setImageDrawable(drawable);
            }else{
                imagen.setImageResource(R.drawable.baseline_visibility_24);

            }
            nombre.setText(carrito.getNombre());
            precio.setText(String.valueOf(carrito.getTotalpago()));
            btndelete.setOnClickListener(v->{
                String idcar=String.valueOf(carrito.getId());
                System.out.println(idcar);
            });
            list.addView(itemview);
        }
        totalpago.setText(String.valueOf(importepagar)+" S/.");
    }
    private void setElementos(){

    }
}