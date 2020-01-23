package com.edgar.fragmentsrobots;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("serial")
public class Robot implements Parcelable {

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

    protected Robot(Parcel in) {
        material = in.readString();
        nombre = in.readString();
        anyo = in.readString();
        tipo = in.readString();
    }

    public static final Creator<Robot> CREATOR = new Creator<Robot>() {
        @Override
        public Robot createFromParcel(Parcel in) {
            return new Robot(in);
        }

        @Override
        public Robot[] newArray(int size) {
            return new Robot[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(material);
        dest.writeString(nombre);
        dest.writeString(anyo);
        dest.writeString(tipo);
    }
}
