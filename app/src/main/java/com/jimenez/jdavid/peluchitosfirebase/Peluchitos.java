package com.jimenez.jdavid.peluchitosfirebase;

/**
 * Created by juand_000 on 6/5/2016.
 */
public class Peluchitos {
    private String id, nombre, cantidad, valor;

    public Peluchitos(String id, String nombre, String cantidad, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valor = valor;
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

    public String getValor() {
        return valor;
    }
}
