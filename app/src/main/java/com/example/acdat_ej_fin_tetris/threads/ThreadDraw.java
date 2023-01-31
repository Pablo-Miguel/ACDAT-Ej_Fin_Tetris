package com.example.acdat_ej_fin_tetris.threads;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class ThreadDraw extends Thread{

    private SurfaceView view;
    private  boolean running;

    public ThreadDraw(SurfaceView view) {
        this.view = view;
        running = false;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (running){
            canvas = null;
            try {
                canvas = view.getHolder().lockCanvas(null);
                if(canvas != null) {
                    synchronized (view.getHolder()) {
                        view.postInvalidate();
                    }
                }
            } finally {
                if (canvas != null) view.getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

}
