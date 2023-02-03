package com.example.acdat_ej_fin_tetris.daos;

import com.example.acdat_ej_fin_tetris.R;
import com.example.acdat_ej_fin_tetris.daos.dlls.TetrisSQLiteHelper;
import com.example.acdat_ej_fin_tetris.pojos.Partida;

import java.util.ArrayList;

public class DaoPartidas {
    private static DaoPartidas dao;
    private static ArrayList<Partida> partidas;
    private static TetrisSQLiteHelper dbHelper;

    public static DaoPartidas getInstance() {

        if (dao == null) {
            dao = new DaoPartidas();
        }

        if (partidas == null) {
            partidas = new ArrayList<Partida>();
        }

        return dao;
    }

    public static void establecerConexion(TetrisSQLiteHelper dbHelper) {
        DaoPartidas.dbHelper = dbHelper;
    }

    public void addPartida(Partida partida){
        dbHelper.savePartida(partida);
    }

    public ArrayList<Partida> getPartidas(){
        return dbHelper.getPartidas();
    }

}
