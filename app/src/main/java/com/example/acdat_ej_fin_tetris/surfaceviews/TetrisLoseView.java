package com.example.acdat_ej_fin_tetris.surfaceviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_ej_fin_tetris.LoseActivity;
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

public class TetrisLoseView extends SurfaceView implements SurfaceHolder.Callback {

    private ThreadDraw threadDraw;
    private ArrayList<Menu> lose_static_menu, lose_touch_menu;
    private LoseActivity activity;
    private Integer level;

    public TetrisLoseView(Context context, LoseActivity activity, Integer level) {
        super(context);

        this.activity = activity;
        this.level = level;

        lose_static_menu = new ArrayList<Menu>();
        lose_touch_menu = new ArrayList<Menu>();

        getHolder().addCallback(this);
        setBackgroundResource(R.drawable.bg_2);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int cont_lose_id  = 0;

        lose_static_menu.add(new MenuBackground((getWidth()/2) - 350, 100, getResources(), R.drawable.game_over_v1, 700, 700));
        lose_touch_menu.add(new ButtonMenu(cont_lose_id++,(getWidth() / 2) - 300, getHeight() - 700, Color.TRANSPARENT, getResources(), R.drawable.btn_new_game, 600, 200));
        lose_touch_menu.add(new ButtonMenu(cont_lose_id++,(getWidth() / 2) - 300, getHeight() - 400, Color.TRANSPARENT, getResources(), R.drawable.btn_back, 600, 200));

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
        for (Menu m: lose_static_menu) {
            m.onDraw(canvas);
        }
        for (Menu m: lose_touch_menu) {
            m.onDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for (Menu m: lose_touch_menu) {
                    if(m.isTouched(x, y)){
                        switch (m.getId()){
                            case 0:
                                activity.startGame(level);
                                break;
                            case 1:
                                activity.startMainActivity();
                                break;
                        }
                    }
                }
                break;
        }

        return true;
    }
}
