package com.example.acdat_ej_fin_tetris.threads;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.acdat_ej_fin_tetris.daos.DaoTetris;
import com.example.acdat_ej_fin_tetris.models.Imagen;
import com.example.acdat_ej_fin_tetris.pojos.Tetris;

public class FiguraActivaThread extends Thread{

    private Canvas canvas;
    private Resources resources;
    private Tetris tetris;
    private Integer contador;
    private Imagen imagen;
    boolean running;

    public FiguraActivaThread(Resources resources, Tetris tetris) {
        this.resources = resources;
        this.tetris = tetris;
        contador = 0;
        this.imagen = new Imagen(DaoTetris.getTam_celda(), DaoTetris.getTam_celda());
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        while (running){
            contador += 1;
            if (contador >= 100)
                contador = 0;

            if(contador % 30 == 0){
                if(!tetris.getPerdido()){
                    tetris.ir_abajo();
                }
            }

            if(tetris.getFigura() != null){
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (tetris.getFigura().getForma().contains(i * 4 + j)) {
                            imagen.setPos_X((DaoTetris.getTam_celda() * (tetris.getFigura().getX() + j)) + DaoTetris.getHor_margen());
                            imagen.setPos_Y((DaoTetris.getTam_celda() * (tetris.getFigura().getY() + i)) + DaoTetris.getVer_margen());
                            imagen.setImg_ref(resources, DaoTetris.getInstance().getImagenes().get(tetris.getFigura().getColor() - 1));
                            imagen.onDraw(canvas);
                        }
                    }
                }
            }
        }
    }
}
