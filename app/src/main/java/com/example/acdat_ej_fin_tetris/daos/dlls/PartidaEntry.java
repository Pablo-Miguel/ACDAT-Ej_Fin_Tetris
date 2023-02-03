package com.example.acdat_ej_fin_tetris.daos.dlls;

import android.provider.BaseColumns;

public abstract class PartidaEntry implements BaseColumns {
    public static final String TABLE_NAME ="Partida";

    public static final String ID_PARTIDA = "id_nombre";
    public static final String NOMBRE = "nombre";
    public static final String SCORE = "score";
}
