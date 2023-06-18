package com.example.proyectominimarket.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectominimarket.usuario;

import java.util.Date;

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
    public DBuser(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        onCreate(db);
    }

    public Boolean insertarDatos(String nombre, String Correo, String Date, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NOMBRE",nombre);
        contentValues.put("NACIMIENTO",Date);
        contentValues.put("CORREO",Correo);
        contentValues.put("PASSWORD",password);
        Long result=db.insert(TABLANAME,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean verificarusuario(String Correo){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLANAME+" WHERE 'CORREO'=? ",new String[]{Correo});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean verificarusuarioyContra(String correo,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLANAME+" WHERE 'CORREO'=? and 'PASSWORD'=?",
                new String[]{correo,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

}
