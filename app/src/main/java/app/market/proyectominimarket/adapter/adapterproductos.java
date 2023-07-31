package app.market.proyectominimarket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.market.proyectominimarket.Db.DBuser;
import app.market.proyectominimarket.R;
import app.market.proyectominimarket.model.car;
import app.market.proyectominimarket.model.producto;

import java.util.ArrayList;

public class adapterproductos extends RecyclerView.Adapter<adapterproductos.ViewHolderDatos> {

    private static final String SHARED_PREF="myaccount";
    ArrayList<producto> productos;

    DBuser database;
    public adapterproductos(ArrayList<producto> productos) {
        this.productos = productos;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombreview,precioview;
        EditText cantidad;
        Button Agregar,incrementar,reducir;

        SharedPreferences sharedPreferences;
        ImageView imagenpro;
        String correo;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //inicializaremos como los otros proyectos del la vista al codigo
            imagenpro=itemView.findViewById(R.id.imageContentproduct);
            nombreview=itemView.findViewById(R.id.txvNombrelist);
            precioview=itemView.findViewById(R.id.txvPreciolist);
            Agregar=itemView.findViewById(R.id.btnagregar);
            incrementar=itemView.findViewById(R.id.btnincrementar);
            reducir=itemView.findViewById(R.id.btnreducir);
            cantidad=itemView.findViewById(R.id.edtcantidad);
            sharedPreferences=itemView.getContext().getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
            database=new DBuser(itemView.getContext());
            correo=sharedPreferences.getString("Correo",null);
        }
    }
    @NonNull
    @Override
    public adapterproductos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //en este apartado ingresaremos los datos registrados en el layout insertado en el productActivity
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product,null,false);
        return new ViewHolderDatos(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull adapterproductos.ViewHolderDatos holder,int position) {
        //AGREGAREMOS LAS IMAGENES DEPENDIENDO DE CADA PRODUCTO
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
        //nombre del producto como su precio y categoria

        holder.nombreview.setText(productos.get(position).getNombre());
        holder.precioview.setText(productos.get(position).getPrecio().toString()+" S/.");

        //LOGICA DE INCREMENTACION Y REDUCCION DE CANTIDAD DE PRODUCTO QUE DESEE COMPRAR.
        holder.incrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidadvalor=Integer.parseInt(holder.cantidad.getText().toString());
                cantidadvalor++;
                holder.cantidad.setText(String.valueOf(cantidadvalor));
            }
        });
        holder.reducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidadvalor=Integer.parseInt(holder.cantidad.getText().toString());
                cantidadvalor--;
                holder.cantidad.setText(String.valueOf(cantidadvalor));
            }
        });

        holder.Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String resultado=productos.get(position).getNombre()+" -- "+
                        productos.get(position).getPrecio().toString().replace("S/.","")+" -- "+
                        holder.cantidad.getText().toString();
                Toast.makeText(v.getContext(), resultado, Toast.LENGTH_LONG).show();
                 */
                int cantidad=Integer.parseInt(holder.cantidad.getText().toString());
                Double precio=Double.parseDouble(productos.get(position).getPrecio().toString());
                car carrito=new car();
                carrito.setId(0);
                carrito.setNombre(productos.get(position).getNombre());
                carrito.setCantidad(cantidad);
                carrito.setPrecio(precio);
                carrito.setTotalpago(precio,cantidad);
                System.out.println(carrito.getNombre()+" "+carrito.getCantidad());
                boolean contenido=database.insertarCarrito(carrito,holder.correo);
                if(contenido){
                    mensajes(v,"se pudo ingresar");
                }else{
                    mensajes(v,"no se pudo ingresar");
                }
                holder.cantidad.setText("1");
                //ingresarlo a la bd de carrito con relacion al usuario

            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
    public void mensajes(View view,String resultado){
        Toast.makeText(view.getContext(), resultado, Toast.LENGTH_SHORT).show();
    }
}
