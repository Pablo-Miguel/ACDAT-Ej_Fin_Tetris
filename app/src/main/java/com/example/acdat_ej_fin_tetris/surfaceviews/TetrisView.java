package com.example.acdat_ej_fin_tetris.surfaceviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_ej_fin_tetris.MainActivity;
import com.example.acdat_ej_fin_tetris.R;
import com.example.acdat_ej_fin_tetris.models.ButtonMenu;
import com.example.acdat_ej_fin_tetris.models.Menu;
import com.example.acdat_ej_fin_tetris.models.MenuBackground;
import com.example.acdat_ej_fin_tetris.threads.ThreadDraw;

import java.util.ArrayList;

public class TetrisView extends SurfaceView implements SurfaceHolder.Callback {

    private ThreadDraw threadDraw;
    private MainActivity activity;
    private ArrayList<Menu> inicio_touch_menu, inicio_static_menu;
    private Integer level;

    public TetrisView(Context context, MainActivity activity) {
        super(context);

        this.activity = activity;

        inicio_touch_menu = new ArrayList<Menu>();
        inicio_static_menu = new ArrayList<Menu>();

        getHolder().addCallback(this);
        setBackgroundResource(R.drawable.bg_3);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int cont_inicio_id = 0;

        inicio_static_menu.add(new MenuBackground((getWidth() / 2) - 400, 200, getResources(), R.drawable.tetris_logo, 800, 642));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) - 200, Color.TRANSPARENT, getResources(), R.drawable.btn_lvl_1, 600, 200));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) + 100, Color.TRANSPARENT, getResources(), R.drawable.btn_lvl_2, 600, 200));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) + 400, Color.TRANSPARENT, getResources(), R.drawable.btn_lvl_3, 600, 200));
        inicio_touch_menu.add(new ButtonMenu(cont_inicio_id++,(getWidth() / 2) - 300, (getHeight() / 2) + 700, Color.TRANSPARENT, getResources(), R.drawable.btn_exit, 600, 200));

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

        for (Menu m : inicio_static_menu){
            m.onDraw(canvas);
        }

        for (Menu m : inicio_touch_menu){
            m.onDraw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for (Menu m : inicio_touch_menu) {
                    if (m.isTouched(x, y)) {
                        switch (m.getId()) {
                            case 0:
                                level = 1;
                                break;
                            case 1:
                                level = 2;
                                break;
                            case 2:
                                level = 3;
                                break;
                            case 3:
                                System.exit(0);
                                break;
                        }
                        activity.startGame(level);
                    }
                }
                break;
        }

        return true;
    }

}
