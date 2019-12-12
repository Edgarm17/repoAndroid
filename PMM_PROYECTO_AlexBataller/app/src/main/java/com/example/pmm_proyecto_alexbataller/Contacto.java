package com.example.pmm_proyecto_alexbataller;


import java.io.Serializable;

public class Contacto implements Serializable {
    private String nombre;
    private String telefono;
    private String id;
    private ContactType ct;

    public Contacto() {
    }

    public Contacto(String nombre, String telefono, String id) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContactType getCt() {
        return ct;
    }

    public void setCt(ContactType ct) {
        this.ct = ct;
    }
}
