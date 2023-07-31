package app.market.proyectominimarket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import app.market.proyectominimarket.Db.DBuser;

import app.market.proyectominimarket.R;

import app.market.proyectominimarket.model.car;

import java.util.ArrayList;

public class Cartfragmento extends Fragment {

    ConstraintLayout vacio,listlayout,buttonlayout;
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
        //obtemos los valores de cada view
        super.onViewCreated(view, savedInstanceState);
        //inicializaremos con los diseños en el xml
        buttonlayout=view.findViewById(R.id.layoutplusmaterial);
        listlayout=view.findViewById(R.id.layoutList);
        list=view.findViewById(R.id.Listlinear);
        vacio=view.findViewById(R.id.layoutvacio);
        Button btnprod=view.findViewById(R.id.btnplus);

        //declaramos una memoria en base a nuestro fragmento


        db=new DBuser(view.getContext());
        sp=view.getContext().getSharedPreferences("myaccount", Context.MODE_PRIVATE);
        String correo=sp.getString("Correo",null);
        btnprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewproducto=new Intent(getContext(),ProductActivity.class);
                viewproducto.putExtra("tipo","");
                startActivity(viewproducto);
            }
        });

        Button btncomprar=view.findViewById(R.id.btncomprar);
        btncomprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=db.deleteCar(correo);
                arrraycar.clear();
                importepagar=0;
                if(res){
                    Toast.makeText(getContext(), "se compro correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "hubo un error", Toast.LENGTH_SHORT).show();
                }
                VerproductoenCarrito();
            }
        });


        //usamos el SharedPreferences para aun seguir guardando compras en base a un usuario


        Cursor cursor=db.obtenerproductosdelcarrito(correo);
        System.out.println(cursor.getCount());


        //en ello se va guiar si dentro del cursor hay mas valores en caso no, se visualizara un
        //mensaje que el carro se encuentra vacio



        if(cursor.getCount()>0){
            arrraycar.clear();
            importepagar=0;
            vacio.setVisibility(View.GONE);
            buttonlayout.setVisibility(View.VISIBLE);
            listlayout.setVisibility(View.VISIBLE);
            //en ello se va ir agregando datos al array mediante cada repeticion del while
            //con ello tambien la suma del precio
            while (cursor.moveToNext()){
                int id=cursor.getInt(0);
                String nombrepro=cursor.getString(1);
                int cantidad=cursor.getInt(2);
                double pagar=cursor.getDouble(3);
                arrraycar.add(new car(id,nombrepro,cantidad,pagar));
                importepagar+=pagar;
            }
            //luego de eso se visualizara los productos que se desea comprar
            VerproductoenCarrito();
        }else{
            vacio.setVisibility(View.VISIBLE);
            buttonlayout.setVisibility(View.INVISIBLE);
            listlayout.setVisibility(View.INVISIBLE);
        }
    }

    private void VerproductoenCarrito() {
        //se va inicializar y declarar el tvde pagar pero se removera todo lo que se encuentre en lista
        TextView totalpago=getView().findViewById(R.id.txvtotalpagar);
        totalpago.setText("");
        list.removeAllViews();
        //se va hacer interacciones con foreach para ir introduciendo los datos en cada uno de los
        //herramientas visuales
        for (car carrito:arrraycar){
            //se va crear una vista en base al xml creado list_car para el grupo de vista que se va a
            //introducir, list
            View itemview=LayoutInflater.from(getContext()).inflate(R.layout.list_car,list,false);
            ImageView imagen=itemview.findViewById(R.id.ImgProduct);
            Button btndelete=itemview.findViewById(R.id.btnborrar);
            TextView cantidad=itemview.findViewById(R.id.tvventaCantidad);
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
            cantidad.setText(String.valueOf(carrito.getCantidad()));


            btndelete.setOnClickListener(v->{
                String idcar=String.valueOf(carrito.getId());
                boolean res=db.eliminarcarrito(idcar);
                if(res){
                    Toast.makeText(getContext(), "se elimino correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "hubo un error", Toast.LENGTH_SHORT).show();
                }
                recargarventa(idcar);
            });
            list.addView(itemview);
        }
        totalpago.setText("Total :"+String.valueOf(importepagar)+" S/.");
    }
    private void recargarventa(String res){
        arrraycar.clear();
        importepagar=0;
        String correo=sp.getString("Correo",null);
        Cursor cursor=db.obtenerproductosdelcarrito(correo);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String nombrepro=cursor.getString(1);
            int cantidad=cursor.getInt(2);
            double pagar=cursor.getDouble(3);
            arrraycar.add(new car(id,nombrepro,cantidad,pagar));
            importepagar+=pagar;
        }
        VerproductoenCarrito();
    }
}