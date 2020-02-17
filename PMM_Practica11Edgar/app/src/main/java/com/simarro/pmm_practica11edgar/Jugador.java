package com.simarro.pmm_practica11edgar;

import java.io.Serializable;

public class Jugador implements Serializable {

    private String nombre,posicion,telefono,numero;


    public Jugador(String nombre,String numero,String telefono, String posicion  ) {
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
