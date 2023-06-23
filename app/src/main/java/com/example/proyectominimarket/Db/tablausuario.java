package com.example.proyectominimarket.Db;

public class tablausuario {
    private static String TABLANAME="usuario";

    public String getTABLANAME() {
        return TABLANAME;
    }

    public static final String CREATE="CREATE TABLE "+TABLANAME+"("+
            "ID INTEGER primary key autoincrement, "+
            "NOMBRE TEXT ,"+
            "NACIMIENTO DATE ,"+
            "CORREO TEXT ,"+
            "PASSWORD TEXT"+
            ")";
    private static final String DROP="DROP TABLE IF EXISTS "+TABLANAME;

    public String creartablausuario(){
        return CREATE;
    }
    public String borrartablausuario(){
        return DROP;
    }
}
