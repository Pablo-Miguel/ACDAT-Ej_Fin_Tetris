package com.example.acdat_ej_fin_tetris.threads;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import com.example.acdat_ej_fin_tetris.daos.DaoTetris;
import com.example.acdat_ej_fin_tetris.models.Imagen;
import com.example.acdat_ej_fin_tetris.pojos.Tetris;

public class FiguraActivaThread extends Thread{

    private Tetris tetris;
    private Integer contador;
    boolean running;

    public FiguraActivaThread(Tetris tetris) {
        this.tetris = tetris;
        contador = 0;
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while(running){
            contador += 1;
            if (contador >= 10000)
                contador = 0;

            if(contador % 30 == 0){
                if(!tetris.getPerdido()){
                    tetris.ir_abajo();
                }
            }
            try {
                sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
