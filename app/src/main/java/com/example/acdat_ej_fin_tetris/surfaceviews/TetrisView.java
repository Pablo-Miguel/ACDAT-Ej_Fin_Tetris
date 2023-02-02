package com.example.acdat_ej_fin_tetris.surfaceviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
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
import com.example.acdat_ej_fin_tetris.threads.FiguraActivaThread;
import com.example.acdat_ej_fin_tetris.threads.ThreadDraw;

import java.util.ArrayList;

public class TetrisView extends SurfaceView implements SurfaceHolder.Callback {

    private ThreadDraw threadDraw;
    private FiguraActivaThread threadFigura;
    private Context context;
    private ArrayList<Menu> static_menu, touch_menu, lose_static_menu, lose_touch_menu,inicio_touch_menu, inicio_static_menu;
    private Integer score, contador, level;
    private Tetris tetris;
    private Imagen imagen;
    private MediaPlayer mediaPlayer;
    private Boolean inicio;

    public TetrisView(Context context) {
        super(context);

        this.context = context;

        score = 0;
        contador = 0;
        inicio_touch_menu = new ArrayList<Menu>();
        inicio_static_menu = new ArrayList<Menu>();
        static_menu = new ArrayList<Menu>();
        touch_menu = new ArrayList<Menu>();
        lose_static_menu = new ArrayList<Menu>();
        lose_touch_menu = new ArrayList<Menu>();

        inicio = true;

        getHolder().addCallback(this);
        setBackgroundResource(R.drawable.bg_3);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int cont_id = 0;
        int cont_inicio_id = 0;
        int cont_lose_id  = 0;

        inicio_static_menu.add(new MenuBackground((getWidth() / 2) - 400, 200, getResources(), R.drawable.tetris_logo, 800, 642));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) - 200, Color.TRANSPARENT, getResources(), R.drawable.btn_lvl_1, 600, 200));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) + 100, Color.TRANSPARENT, getResources(), R.drawable.btn_lvl_2, 600, 200));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) + 400, Color.TRANSPARENT, getResources(), R.drawable.btn_lvl_3, 600, 200));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) + 700, Color.TRANSPARENT, getResources(), R.drawable.btn_exit, 600, 200));

        static_menu.add(new MenuBackground(0, getHeight() - 300, getResources(), R.drawable.menu_v2, getWidth(), 300));
        static_menu.add(new TextMenu(getWidth()/2 - 100, getHeight() - 250, Color.BLACK, "Score: 0"));
        touch_menu.add(new ButtonMenu(cont_id++,350, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.rotar_1, 150, 150));
        touch_menu.add(new ButtonMenu(cont_id++, getWidth() - 500, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.bajar_1, 150, 150));
        touch_menu.add(new ButtonMenu(cont_id++, 100, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.btn_izq, 150, 150));
        touch_menu.add(new ButtonMenu(cont_id++, getWidth() - 250, getHeight() - 200, Color.TRANSPARENT, getResources(), R.drawable.btn_der, 150, 150));

        lose_static_menu.add(new MenuBackground((getWidth()/2) - 350, 100, getResources(), R.drawable.game_over_v1, 700, 700));
        lose_touch_menu.add(new ButtonMenu(cont_lose_id++,(getWidth() / 2) - 300, getHeight() - 700, Color.TRANSPARENT, getResources(), R.drawable.btn_new_game, 600, 200));
        lose_touch_menu.add(new ButtonMenu(cont_lose_id++,(getWidth() / 2) - 300, getHeight() - 400, Color.TRANSPARENT, getResources(), R.drawable.btn_back, 600, 200));

        DaoTetris.setHor_margen((getWidth() - (DaoTetris.getTam_celda() * 10)) / 2);
        MenuBackground mb = (MenuBackground) static_menu.get(0);
        DaoTetris.setVer_margen(((getHeight() - mb.getAltura()) - (DaoTetris.getTam_celda() * 20)) / 2);

        imagen = new Imagen(DaoTetris.getTam_celda(), DaoTetris.getTam_celda());

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
        if(!inicio){
            if(!tetris.getPerdido()){
                tetris.onDraw(canvas);

                threadFigura.setRunning(true);

                for (int x = 0; x < tetris.getFilas(); x++){
                    for (int y = 0; y < tetris.getColumnas(); y++){
                        if(tetris.getTablero().get(x).get(y) > 0){
                            imagen.setPos_X((y * DaoTetris.getTam_celda()) + DaoTetris.getHor_margen());
                            imagen.setPos_Y((x * DaoTetris.getTam_celda()) + DaoTetris.getVer_margen());
                            imagen.setImg_ref(getResources(), DaoTetris.getInstance().getImagenes().get(tetris.getTablero().get(x).get(y) - 1));
                            imagen.onDraw(canvas);
                        }
                    }
                }

                if(tetris.getFigura() != null){
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (tetris.getFigura().getForma().contains(i * 4 + j)) {
                                imagen.setPos_X((DaoTetris.getTam_celda() * (tetris.getFigura().getX() + j)) + DaoTetris.getHor_margen());
                                imagen.setPos_Y((DaoTetris.getTam_celda() * (tetris.getFigura().getY() + i)) + DaoTetris.getVer_margen());
                                imagen.setImg_ref(getResources(), DaoTetris.getInstance().getImagenes().get(tetris.getFigura().getColor() - 1));
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
            } else {
                setBackgroundResource(R.drawable.bg_2);
                for (Menu m: lose_static_menu) {
                    m.onDraw(canvas);
                }
                for (Menu m: lose_touch_menu) {
                    m.onDraw(canvas);
                }
            }
        } else {
            setBackgroundResource(R.drawable.bg_3);

            for (Menu m : inicio_static_menu){
                m.onDraw(canvas);
            }

            for (Menu m : inicio_touch_menu){
                m.onDraw(canvas);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!inicio) {
                    if (!tetris.getPerdido()) {
                        for (Menu m : touch_menu) {
                            if (m.isTouched(x, y)) {
                                switch (m.getId()) {
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
                    } else {
                        for (Menu m: lose_touch_menu) {
                            if(m.isTouched(x, y)){
                                switch (m.getId()){
                                    case 0:
                                        tetris = new Tetris(20, 10);
                                        threadFigura.setRunning(false);
                                        threadFigura = new FiguraActivaThread(tetris, level);
                                        threadFigura.setRunning(true);
                                        threadFigura.start();
                                        setBackgroundResource(R.drawable.bg_v1);
                                        break;
                                    case 1:
                                        setBackgroundResource(R.drawable.bg_1);
                                        mediaPlayer.stop();
                                        inicio = true;
                                        break;
                                }
                            }
                        }
                    }
                } else {
                    for (Menu m : inicio_touch_menu) {
                        if (m.isTouched(x, y)) {
                            setBackgroundResource(R.drawable.bg_1);
                            switch (m.getId()) {
                                case 0:
                                    mediaPlayer = MediaPlayer.create(context, R.raw.nivel1);
                                    level = 1;
                                    break;
                                case 1:
                                    mediaPlayer = MediaPlayer.create(context, R.raw.nivel2);
                                    level = 2;
                                    break;
                                case 2:
                                    mediaPlayer = MediaPlayer.create(context, R.raw.nivel3);
                                    level = 3;
                                    break;
                                case 3:
                                    System.exit(0);
                                    break;
                            }
                            mediaPlayer.setLooping(true);
                            mediaPlayer.start();
                            tetris = new Tetris(20, 10);
                            threadFigura = new FiguraActivaThread(tetris, level);
                            threadFigura.setRunning(true);
                            threadFigura.start();
                            inicio = false;
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
