package com.example.acdat_ej_fin_tetris.models;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Menu {
    private Integer id;
    private float pos_X, pos_Y;
    private Paint paint;

    public Menu(float pos_X, float pos_Y, int color) {
        this.id = null;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(color);
    }

    public Menu(Integer id, float pos_X, float pos_Y, int color) {
        this.id = id;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(color);
    }

    public abstract void onDraw(Canvas canvas);

    public abstract boolean isTouched(int coord_X, int coord_y);

    public Integer getId() {
        return id;
    }

    public float getPos_X() {
        return pos_X;
    }

    public void setPos_X(float pos_X) {
        this.pos_X = pos_X;
    }

    public float getPos_Y() {
        return pos_Y;
    }

    public void setPos_Y(float pos_Y) {
        this.pos_Y = pos_Y;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

}
