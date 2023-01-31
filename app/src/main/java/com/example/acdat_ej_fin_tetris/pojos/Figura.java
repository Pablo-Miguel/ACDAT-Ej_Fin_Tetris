package com.example.acdat_ej_fin_tetris.pojos;

import android.util.Log;

import com.example.acdat_ej_fin_tetris.daos.DaoTetris;

import java.util.ArrayList;
import java.util.Random;

public class Figura {
    Random rnd = new Random();
    private Integer x, y, color, giro;
    private ArrayList<ArrayList<Integer>> forma;

    public Figura(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        Integer tipo = rnd.nextInt(DaoTetris.getInstance().getFiguras().size());
        forma = DaoTetris.getInstance().getFiguras().get(tipo);

        color = tipo + 1;
        giro = 0;
    }

    public ArrayList<Integer> getForma(){
        return forma.get(giro);
    }

    public void girar(){
        giro = (giro + 1) % forma.size();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getGiro() {
        return giro;
    }

    public void setGiro(Integer giro) {
        this.giro = giro;
    }
}
