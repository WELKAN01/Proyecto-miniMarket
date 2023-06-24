package com.example.proyectominimarket.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectominimarket.model.producto;
import com.example.proyectominimarket.model.usuario;

import java.util.ArrayList;
import java.util.List;

public class DBuser extends SQLiteOpenHelper {
    private static String DATABASE="MARKET.bd";
    private static String TABLANAME="usuario";

    private static String CREATE="CREATE TABLE "+TABLANAME+"("+
            "ID INTEGER primary key autoincrement, "+
            "NOMBRE TEXT ,"+
            "NACIMIENTO DATE ,"+
            "CORREO TEXT ,"+
            "PASSWORD TEXT"+
            ")";
    private static String DROP="DROP TABLE IF EXISTS "+TABLANAME;

    private tablaproducto Tablaproducto=new tablaproducto();
    private tablausuario Tablausuario=new tablausuario();
    public DBuser(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    //Creacion de las tablas usuario y productos de la base de datos
    //se va establecer datos de producto de forma predeterminado al programa
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tablausuario.creartablausuario());
        db.execSQL(Tablaproducto.creartablaproducto());
        insertarproductos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Tablausuario.borrartablausuario());
        db.execSQL(Tablaproducto.Eliminartablaproducto());
        onCreate(db);

    }

    //registro de cuenta de una persona
    public Boolean insertarDatos(String nombre, String Correo, String Date, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NOMBRE",nombre);
        contentValues.put("NACIMIENTO",Date);
        contentValues.put("CORREO",Correo);
        contentValues.put("PASSWORD",password);
        Long result=db.insert(Tablausuario.getTABLANAME()+"",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    //verificar si usuario existe en la parte de perfil

    public usuario verificarusuario(String Correo){

        SQLiteDatabase db=this.getWritableDatabase();
        usuario user = null;
        Cursor cursor=db.rawQuery("SELECT NOMBRE,CORREO,NACIMIENTO FROM "+Tablausuario.getTABLANAME()+" WHERE CORREO=? ",new String[]{Correo});
        if (cursor.moveToFirst()){
            String nombre=cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
            String correo=cursor.getString(cursor.getColumnIndexOrThrow("CORREO"));
            String nacer=cursor.getString(cursor.getColumnIndexOrThrow("NACIMIENTO"));
            user=new usuario(nombre,correo,nacer);
        }
        cursor.close();
        return user;
    }

    //verificar si usuario y contraseña existe
    public boolean verificarusuarioyContra(String correo,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Tablausuario.getTABLANAME()+" WHERE CORREO=? and PASSWORD=?",
                new String[]{correo,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    //funcion de insertacion de productos
    public void insertarproductos(SQLiteDatabase db){
        List<ContentValues> listproducto=new ArrayList<>();
        ContentValues cv1=new ContentValues();
        cv1.put("NOMBRE","coca cola 550ml");
        cv1.put("PRECIO",3.50);
        cv1.put("STOCK",15);
        cv1.put("CATEGORIA","bebidas");
        listproducto.add(cv1);
        ContentValues cv2=new ContentValues();
        cv2.put("NOMBRE","inka cola 550ml");
        cv2.put("PRECIO",3.50);
        cv2.put("STOCK",15);
        cv2.put("CATEGORIA","bebidas");
        listproducto.add(cv2);
        ContentValues cv3=new ContentValues();
        cv3.put("NOMBRE","zanahoria 1u");
        cv3.put("PRECIO",0.80);
        cv3.put("STOCK",25);
        cv3.put("CATEGORIA","vegetales");
        listproducto.add(cv3);
        ContentValues cv4=new ContentValues();
        cv4.put("NOMBRE","choclo x kg");
        cv4.put("PRECIO",4.00);
        cv4.put("STOCK",15);
        cv4.put("CATEGORIA","vegetales");
        listproducto.add(cv4);
        ContentValues cv5=new ContentValues();
        cv5.put("NOMBRE","manzana x unidad");
        cv5.put("PRECIO",1.00);
        cv5.put("STOCK",15);
        cv5.put("CATEGORIA","frutas");
        listproducto.add(cv5);
        ContentValues cv6=new ContentValues();
        cv6.put("NOMBRE","platano x kilo");
        cv6.put("PRECIO",6.00);
        cv6.put("STOCK",15);
        cv6.put("CATEGORIA","frutas");
        listproducto.add(cv6);
        ContentValues cv7=new ContentValues();
        cv7.put("NOMBRE","pan bimbo 25unidades");
        cv7.put("PRECIO",5.00);
        cv7.put("STOCK",10);
        cv7.put("CATEGORIA","almacen");
        listproducto.add(cv7);
        ContentValues cv8=new ContentValues();
        cv8.put("NOMBRE","aceite 1L");
        cv8.put("PRECIO",7.00);
        cv8.put("STOCK",10);
        cv8.put("CATEGORIA","almacen");
        listproducto.add(cv8);
        for (ContentValues values:listproducto){
            db.insert(Tablaproducto.getTABLANAME(),null,values);
        }
    }

    public List<producto> obtenerproducto(String categoria){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        List<producto> prod=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT NOMBRE,PRECIO,STOCK,CATEGORIA FROM "+
                        Tablaproducto.getTABLANAME()+
                        " WHERE CATEGORIA=?"
                ,new String[]{categoria});
        while (cursor.moveToNext()){
            String nombre=cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
            Double precio=cursor.getDouble(cursor.getColumnIndexOrThrow("PRECIO"));
            int stock=cursor.getInt(cursor.getColumnIndexOrThrow("STOCK"));
            String Categoria=cursor.getString(cursor.getColumnIndexOrThrow("CATEGORIA"));
            prod.add(new producto(nombre,precio,stock,categoria));
        }
        return prod;
    }
}
