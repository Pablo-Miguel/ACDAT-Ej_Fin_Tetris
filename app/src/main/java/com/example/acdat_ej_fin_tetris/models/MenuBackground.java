package com.example.acdat_ej_fin_tetris.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class MenuBackground extends Menu {

    private Bitmap img;
    private int base, altura;

    public MenuBackground(float pos_X, float pos_Y, Resources resources, int img_ref, int base, int altura) {
        super(pos_X, pos_Y, 0);
        this.base = base;
        this.altura = altura;
        setImg_ref(resources, img_ref);
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

    public void setImg_ref(Resources resources, Integer img_ref) {
        Bitmap bmp = BitmapFactory.decodeResource(resources, img_ref);
        img = bmp.createScaledBitmap(bmp, base, altura, true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(img, getPos_X(), getPos_Y(), null);
    }

    @Override
    public boolean isTouched(int coord_X, int coord_y) {
        return false;
    }

}
