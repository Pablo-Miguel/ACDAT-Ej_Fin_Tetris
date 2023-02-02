package com.example.acdat_ej_fin_tetris.pojos;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.acdat_ej_fin_tetris.daos.DaoTetris;

import java.util.ArrayList;

public class Tetris {

    private Integer filas, columnas, puntos;
    private ArrayList<ArrayList<Integer>> tablero;
    private Figura figura;
    private Boolean perdido;
    private Paint paint;
    private Boolean started;

    public Tetris() {
        this.filas = 20;
        this.columnas = 10;
        this.tablero = tablero_modelo();
        nueva_figura();
        this.puntos = 0;
        this.perdido = false;
        this.paint = new Paint();
        paint.setColor(Color.GRAY);
        started = false;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Integer getFilas() {
        return filas;
    }

    public void setFilas(Integer filas) {
        this.filas = filas;
    }

    public Integer getColumnas() {
        return columnas;
    }

    public void setColumnas(Integer columnas) {
        this.columnas = columnas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public ArrayList<ArrayList<Integer>> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Integer>> tablero) {
        this.tablero = tablero;
    }

    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    public Boolean getPerdido() {
        return perdido;
    }

    public void setPerdido(Boolean perdido) {
        this.perdido = perdido;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    private ArrayList<ArrayList<Integer>> tablero_modelo() {
        ArrayList<ArrayList<Integer>> tablero = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < filas; i++) {
            tablero.add(new ArrayList<Integer>());
            for (int j = 0; j < columnas; j++)
                tablero.get(i).add(0);
        }
        return tablero;
    }

    public void onDraw(Canvas canvas){
        started = true;
        for(int i = 0; i < filas + 1; i++)
            canvas.drawLine(
                    DaoTetris.getHor_margen(),
                    (DaoTetris.getTam_celda() * i) + DaoTetris.getVer_margen(),
                    (DaoTetris.getTam_celda() * 10) + DaoTetris.getHor_margen(),
                    (DaoTetris.getTam_celda() * i) + DaoTetris.getVer_margen(),
                    paint);
        for(int j = 0; j < columnas + 1; j++)
            canvas.drawLine(
                    (DaoTetris.getTam_celda() * j) + DaoTetris.getHor_margen(),
                    DaoTetris.getVer_margen(),
                    (DaoTetris.getTam_celda() * j) + DaoTetris.getHor_margen(),
                    (DaoTetris.getTam_celda() * 20) + DaoTetris.getVer_margen(),
                    paint);
    }

    public void nueva_figura() {
        this.figura = new Figura(3, 0);
    }

    public Boolean se_choca() {
        Boolean es_chocado = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (figura.getForma().contains(i * 4 + j)){
                    if (i + figura.getY() > filas - 1
                            || j + figura.getX() > columnas - 1
                            || j + figura.getX() < 0
                            || tablero.get(i + figura.getY()).get(j + figura.getX()) > 0)
                        es_chocado = true;
                }
            }
        }
        return es_chocado;
    }

    public void eliminar_linea() {

        Boolean recursivo = false;

        for(int fila = filas - 1; fila > 0; fila--) {

            Boolean fila_completa = true;

            for(int columna = 0; columna < columnas; columna++) {
                if(tablero.get(fila).get(columna) == 0)
                    fila_completa = false;
            }

            if(fila_completa) {
                tablero.remove(fila);
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for (int i = 0; i < 10; i++)
                    temp.add(0);
                tablero.add(0, temp);
                puntos += 10;
                recursivo = true;
            }
        }

        if(recursivo)
            eliminar_linea();
    }

    public void inmovilizar(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (figura.getForma().contains(i * 4 + j)) {
                    tablero.get(i + figura.getY()).set(j + figura.getX(), figura.getColor());
                }
            }
        }
        eliminar_linea();
        nueva_figura();
        if (se_choca())
            perdido = true;
    }

    public void ir_rapido_abajo(){
        while(!se_choca())
            figura.setY(figura.getY() + 1);
        figura.setY(figura.getY() - 1);
        inmovilizar();
    }

    public void ir_abajo(){
        figura.setY(figura.getY() + 1);
        if(se_choca()){
            figura.setY(figura.getY() - 1);
            inmovilizar();
        }
    }

    public void ir_a_un_lado(int dx){
        figura.setX(figura.getX() + dx);
        if(se_choca())
            figura.setX(figura.getX() - dx);
    }

    public void girar(){
        Integer giro = figura.getGiro();
        figura.girar();
        if(se_choca())
            figura.setGiro(giro);
    }

}