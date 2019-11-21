package com.simarro.pmm_practica6edgargarcia;

public class Robot {

    private String nombre;
    private String tipo;
    private String material;
    private int anyo;

    public Robot(String nombre, String tipo, String material, int anyo){
        this.nombre = nombre;
        this.tipo = tipo;
        this.material = material;
        this.anyo = anyo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
}
