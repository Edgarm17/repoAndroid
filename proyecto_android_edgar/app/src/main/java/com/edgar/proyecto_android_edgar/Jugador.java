package com.edgar.proyecto_android_edgar;

import android.os.Parcelable;

import java.io.Serializable;

public class Jugador implements Serializable {

    private String nombre,posicion,telefono;
    private int numero;

    public Jugador(String nombre, String posicion, String telefono, int numero) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.telefono = telefono;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
