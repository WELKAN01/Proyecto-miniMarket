package com.example.proyectominimarket;

import java.util.Date;

public class usuario {
    private int id;
    private String nombre;
    private String correo;
    private String password;
    private String nacimiento;

    public usuario() {
    }

    public usuario(String nombre, String correo, String nacimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.nacimiento = nacimiento;
    }

    public usuario(String nombre, String correo, String password, String nacimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }
}
