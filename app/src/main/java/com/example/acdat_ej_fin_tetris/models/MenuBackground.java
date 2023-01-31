package com.example.acdat_ej_fin_tetris.models;

import android.graphics.Canvas;

public class MenuBackground extends Menu {

    private int base, altura;

    public MenuBackground(float pos_X, float pos_Y, int color, int base, int altura) {
        super(pos_X, pos_Y, color);
        this.base = base;
        this.altura = altura;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(super.getPos_X(), super.getPos_Y(),
                super.getPos_X() + this.getBase(),
                super.getPos_Y() + this.getAltura(),
                super.getPaint());
    }

    @Override
    public boolean isTouched(int coord_X, int coord_y) {
        return false;
    }

}
