package com.example.acdat_ej_fin_tetris.daos.dlls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.acdat_ej_fin_tetris.pojos.Partida;

import java.util.ArrayList;

public class TetrisSQLiteHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TetrisServidor.db";

    public TetrisSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + PartidaEntry.TABLE_NAME + " ("
                + PartidaEntry.ID_PARTIDA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PartidaEntry.NOMBRE + " TEXT NOT NULL, "
                + PartidaEntry.SCORE + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_anterior, int version_nueva) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PartidaEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long savePartida(Partida partida){
        long id = 0;

        SQLiteDatabase db = getWritableDatabase();
        db.insert(PartidaEntry.TABLE_NAME, null, partida.toContentValues());

        return id;

    }

    public ArrayList<Partida> getPartidas() {

        ArrayList<Partida> partidas = new ArrayList<Partida>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + PartidaEntry.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{

                partidas.add(new Partida(cursor.getString(1), cursor.getInt(2)));

            } while(cursor.moveToNext());
        }

        cursor.close();

        return partidas;

    }

}
