package com.example.acdat_ej_fin_tetris.threads;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import com.example.acdat_ej_fin_tetris.daos.DaoTetris;
import com.example.acdat_ej_fin_tetris.models.Imagen;
import com.example.acdat_ej_fin_tetris.pojos.Tetris;

public class FiguraActivaThread extends Thread{

    private Tetris tetris;
    private Integer contador, fps;
    private Boolean running;

    public FiguraActivaThread(Tetris tetris, Integer level) {
        this.tetris = tetris;
        contador = 0;
        running = false;
        switch (level){
            case 1:
                fps = 25;
                break;
            case 2:
                fps = 15;
                break;
            case 3:
                fps = 5;
                break;
        }
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
                sleep(fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
