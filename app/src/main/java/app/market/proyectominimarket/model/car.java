package app.market.proyectominimarket.model;

import java.text.DecimalFormat;

public class car {
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    private double totalpago;

    public car() {
    }

    public car(String nombre, int cantidad, double precio, double totalpago) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.totalpago = totalpago;
    }

    public car(int id, String nombre, int cantidad, double totalpago) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.totalpago = totalpago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(double precio,int cantidad) {
        DecimalFormat decimalFormat=new DecimalFormat("#.##");
        double priceres= Double.parseDouble(decimalFormat.format(precio));
        this.totalpago = priceres * cantidad;
    }

}
