package com.simarro.pmm_practica11edgar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteClass extends SQLiteOpenHelper {

    private final static String DB_NAME = "bd_jugadores.sqlite";
    private final static int DB_VERSION = 1;


    public MySQLiteClass(@Nullable Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Jugador (nombre TEXT, numero TEXT, telefono TEXT, posicion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Jugador");

        db.execSQL("CREATE TABLE Jugador (nombre TEXT, numero TEXT, telefono TEXT, posicion TEXT)");

    }
}