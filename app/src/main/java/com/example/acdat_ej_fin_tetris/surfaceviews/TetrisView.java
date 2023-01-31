package com.example.acdat_ej_fin_tetris.surfaceviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_ej_fin_tetris.R;
import com.example.acdat_ej_fin_tetris.daos.DaoTetris;
import com.example.acdat_ej_fin_tetris.models.ButtonMenu;
import com.example.acdat_ej_fin_tetris.models.Imagen;
import com.example.acdat_ej_fin_tetris.models.Menu;
import com.example.acdat_ej_fin_tetris.models.MenuBackground;
import com.example.acdat_ej_fin_tetris.models.TextMenu;
import com.example.acdat_ej_fin_tetris.pojos.Tetris;
import com.example.acdat_ej_fin_tetris.threads.ThreadDraw;

import java.util.ArrayList;

public class TetrisView extends SurfaceView implements SurfaceHolder.Callback {

    private ThreadDraw threadDraw;
    private ArrayList<Menu> static_menu, touch_menu;
    private Integer score, contador;
    private Tetris tetris;
    private Imagen imagen;

    public TetrisView(Context context) {
        super(context);

        score = 0;
        contador = 0;
        static_menu = new ArrayList<Menu>();
        touch_menu = new ArrayList<Menu>();

        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int cont_id = 0;

        static_menu.add(new MenuBackground(0, getHeight() - 300, Color.GREEN, getWidth(), 300));
        static_menu.add(new TextMenu(getWidth()/2 - 100, getHeight() - 250, Color.BLACK, "Score: 0"));
        touch_menu.add(new ButtonMenu(cont_id++,350, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.rotar_1, 150, 150));
        touch_menu.add(new ButtonMenu(cont_id++, getWidth() - 500, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.bajar_1, 150, 150));
        touch_menu.add(new ButtonMenu(cont_id++, 100, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.btn_izq, 150, 150));
        touch_menu.add(new ButtonMenu(cont_id++, getWidth() - 250, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.btn_der, 150, 150));

        DaoTetris.setTam_celda(95);

        tetris = new Tetris(20, 10, this);

        threadDraw = new ThreadDraw(this);
        threadDraw.setRunning(true);
        threadDraw.start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        threadDraw.setRunning(false);

        while (retry){
            try {
                threadDraw.join();
                retry = false;
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        contador += 1;
        if (contador >= 100)
            contador = 0;

        canvas.drawColor(Color.BLACK);

        if(contador % 30 == 0){
            if(!tetris.getPerdido()){
                tetris.ir_abajo();
            }
        }

        tetris.onDraw(canvas);

        for (int x = 0; x < tetris.getFilas(); x++){
            for (int y = 0; y < tetris.getColumnas(); y++){
                if(tetris.getTablero().get(x).get(y) > 0){
                    imagen = new Imagen(
                            y * DaoTetris.getTam_celda(),
                            x * DaoTetris.getTam_celda(),
                            getResources(),
                            DaoTetris.getInstance().getImagenes().get(tetris.getTablero().get(x).get(y) - 1),
                            DaoTetris.getTam_celda(),
                            DaoTetris.getTam_celda()
                    );
                    imagen.onDraw(canvas);
                }
            }
        }

        if(tetris.getFigura() != null){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tetris.getFigura().getForma().contains(i * 4 + j)) {
                        float x = DaoTetris.getTam_celda() * (tetris.getFigura().getX() + j);
                        float y = DaoTetris.getTam_celda() * (tetris.getFigura().getY() + i);
                        imagen = new Imagen(
                                x, y,
                                getResources(),
                                DaoTetris.getInstance().getImagenes().get(tetris.getFigura().getColor() - 1),
                                DaoTetris.getTam_celda(),
                                DaoTetris.getTam_celda()
                        );
                        imagen.onDraw(canvas);
                    }
                }
            }
        }

        TextMenu tm = (TextMenu) static_menu.get(1);
        tm.setText("Score: " + tetris.getPuntos());

        for (Menu m: static_menu) {
            m.onDraw(canvas);
        }

        for (Menu m: touch_menu) {
            m.onDraw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(Menu m : touch_menu){
                    if(m.isTouched(x, y)){
                        switch (m.getId()){
                            case 0:
                                tetris.girar();
                                break;
                            case 1:
                                tetris.ir_rapido_abajo();
                                break;
                            case 2:
                                tetris.ir_a_un_lado(-1);
                                break;
                            case 3:
                                tetris.ir_a_un_lado(1);
                                break;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }

}
