package com.example.proyectominimarket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectominimarket.R;
import com.example.proyectominimarket.model.producto;

import java.util.ArrayList;

public class adapterproductos extends RecyclerView.Adapter<adapterproductos.ViewHolderDatos> {

    ArrayList<producto> productos;

    public adapterproductos(ArrayList<producto> productos) {
        this.productos = productos;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombreview,precioview,categoriaview;
        Button Agregar;

        ImageView imagenpro;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            imagenpro=itemView.findViewById(R.id.imageContentproduct);
            nombreview=itemView.findViewById(R.id.txvNombrelist);
            precioview=itemView.findViewById(R.id.txvPreciolist);
            categoriaview=itemView.findViewById(R.id.txvCategoriaList);
            Agregar=itemView.findViewById(R.id.btnagregar);
        }
    }



    @NonNull
    @Override
    public adapterproductos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product,null,false);
        return new ViewHolderDatos(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull adapterproductos.ViewHolderDatos holder,int position) {
        int resourceid=holder.itemView.
                getContext().
                getResources().
                getIdentifier(
                productos.get(position).getNombre().replace(" ",""),
                        "drawable",
                        holder.itemView.getContext().getPackageName()
                );

        if(resourceid!=0){
            Drawable drawable=holder.itemView.getContext().getResources().getDrawable(resourceid);
            holder.imagenpro.setImageDrawable(drawable);
        }else{
            holder.imagenpro.setImageResource(R.drawable.baseline_visibility_24);
        }
        holder.nombreview.setText(productos.get(position).getNombre());
        holder.precioview.setText(productos.get(position).getPrecio().toString());
        holder.categoriaview.setText(productos.get(position).getCategoria());
        holder.Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public int getResourceName(Context context, String name){
        return context.getResources().getIdentifier(name,"drawable",context.getPackageName());
    }
}
