package com.example.proyectominimarket.model;

public class producto {
    private int id;
    private String nombre;
    private String precio;
    private String stock;
    private String categoria;

    public producto() {
    }

    public producto(String nombre, String precio, String stock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
}
