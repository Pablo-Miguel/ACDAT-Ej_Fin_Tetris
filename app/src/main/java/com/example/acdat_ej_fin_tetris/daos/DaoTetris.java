package com.example.acdat_ej_fin_tetris.daos;

import android.util.Log;

import com.example.acdat_ej_fin_tetris.R;

import java.util.ArrayList;

public class DaoTetris {
    private static DaoTetris dao;
    private static ArrayList<ArrayList<ArrayList<Integer>>> figuras;
    private static ArrayList<Integer> imagenes;
    private static Integer tam_celda = 0;

    public static DaoTetris getInstance() {

        if (dao == null) {
            dao = new DaoTetris();
        }

        if(figuras == null){
            figuras = new ArrayList<ArrayList<ArrayList<Integer>>>();
        }

        if(imagenes == null){
            imagenes = new ArrayList<Integer>();
        }

        return dao;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> getFiguras(){
        if(figuras.isEmpty()) {
            ArrayList<ArrayList<Integer>> content = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(5);
            fig.add(9);
            fig.add(13);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(4);
            fig.add(5);
            fig.add(6);
            fig.add(7);
            content.add(fig);
            figuras.add(content);
            content = new ArrayList<ArrayList<Integer>>();
            fig = new ArrayList<Integer>();
            fig.add(4);
            fig.add(5);
            fig.add(9);
            fig.add(10);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(2);
            fig.add(6);
            fig.add(5);
            fig.add(9);
            content.add(fig);
            figuras.add(content);
            content = new ArrayList<ArrayList<Integer>>();
            fig = new ArrayList<Integer>();
            fig.add(6);
            fig.add(7);
            fig.add(9);
            fig.add(10);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(5);
            fig.add(6);
            fig.add(10);
            content.add(fig);
            figuras.add(content);
            content = new ArrayList<ArrayList<Integer>>();
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(2);
            fig.add(5);
            fig.add(9);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(0);
            fig.add(4);
            fig.add(5);
            fig.add(6);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(5);
            fig.add(9);
            fig.add(8);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(4);
            fig.add(5);
            fig.add(6);
            fig.add(10);
            content.add(fig);
            figuras.add(content);
            content = new ArrayList<ArrayList<Integer>>();
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(2);
            fig.add(6);
            fig.add(10);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(5);
            fig.add(6);
            fig.add(7);
            fig.add(9);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(2);
            fig.add(6);
            fig.add(10);
            fig.add(11);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(3);
            fig.add(5);
            fig.add(6);
            fig.add(7);
            content.add(fig);
            figuras.add(content);
            content = new ArrayList<ArrayList<Integer>>();
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(4);
            fig.add(5);
            fig.add(6);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(4);
            fig.add(5);
            fig.add(9);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(4);
            fig.add(5);
            fig.add(6);
            fig.add(9);
            content.add(fig);
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(5);
            fig.add(6);
            fig.add(9);
            content.add(fig);
            figuras.add(content);
            content = new ArrayList<ArrayList<Integer>>();
            fig = new ArrayList<Integer>();
            fig.add(1);
            fig.add(2);
            fig.add(5);
            fig.add(6);
            content.add(fig);
            figuras.add(content);
        }
        return figuras;
    }

    public ArrayList<Integer> getImagenes(){
        if(imagenes.isEmpty()){
            imagenes.add(R.drawable.sq_1);
            imagenes.add(R.drawable.sq_2);
            imagenes.add(R.drawable.sq_3);
            imagenes.add(R.drawable.sq_4);
            imagenes.add(R.drawable.sq_5);
            imagenes.add(R.drawable.sq_6);
            imagenes.add(R.drawable.sq_7);
        }
        return imagenes;
    }

    public static Integer getTam_celda() {
        return tam_celda;
    }

    public static void setTam_celda(Integer tam_celda) {
        DaoTetris.tam_celda = tam_celda;
    }
}
