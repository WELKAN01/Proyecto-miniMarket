package app.market.proyectominimarket;

import android.content.Context;
import android.content.SharedPreferences;
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

import app.market.proyectominimarket.Db.DBuser;

import app.market.proyectominimarket.R;

import app.market.proyectominimarket.model.usuario;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Perfilfragmento extends Fragment {
    TextView nombre,fechanacimiento,correo;
    private static final String SHARED_PREF="myaccount";
    private static final String CORREO_LOG="Correo";
    private static final String PASSWORD_LOG="Pass";
    SharedPreferences sharedPreferences;
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
        sharedPreferences=view.getContext().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String correosh=sharedPreferences.getString(CORREO_LOG,null);
        String Passwordsh=sharedPreferences.getString(PASSWORD_LOG,null);

        //String item=getActivity().getIntent().getExtras().getString("correo");
        nombre=view.findViewById(R.id.txvNombreperfil);
        fechanacimiento=view.findViewById(R.id.txvEdadperfil);
        correo=view.findViewById(R.id.txvCorreoperfil);
        user=new DBuser(getContext());
        usuario date= user.verificarusuario(correosh);
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