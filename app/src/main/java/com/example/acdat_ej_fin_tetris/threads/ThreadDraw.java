package com.example.acdat_ej_fin_tetris.threads;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class ThreadDraw extends Thread{

    static final long FPS = 60;
    boolean running;
    private SurfaceView view;

    public ThreadDraw(SurfaceView view) {
        this.view = view;
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
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        Canvas canvas;

        while (running){
            canvas = null;
            startTime = System.currentTimeMillis();
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
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

            try {

                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);

            } catch (Exception e) {}
        }
    }

}
