package com.simarro.practicaclasse;

public class Robot {

    private String material;
    private String nombre;
    private String anyo;
    private String tipo;

    public Robot(String nombre, String material, String anyo, String tipo) {
        this.material = material;
        this.nombre = nombre;
        this.anyo = anyo;
        this.tipo = tipo;
    }

    public Robot() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
