package com.jimenez.jdavid.peluchitosfirebase;

/**
 * Created by juand_000 on 6/5/2016.
 */
public class Ventas {
    private String id, nombre, cantidad, total;

    public Ventas(String id, String nombre, String cantidad, String total) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getTotal() {
        return total;
    }
}
