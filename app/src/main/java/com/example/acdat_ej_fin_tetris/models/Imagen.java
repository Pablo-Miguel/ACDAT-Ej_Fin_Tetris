package com.example.acdat_ej_fin_tetris.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Imagen {
    private float pos_X, pos_Y;
    private Paint paint;
    private Bitmap img;
    private int base, altura;

    public Imagen(float pos_X, float pos_Y, Resources resources, int img_ref, int base, int altura) {
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.base = base;
        this.altura = altura;
        Bitmap bmp = BitmapFactory.decodeResource(resources, img_ref);
        img = bmp.createScaledBitmap(bmp, base, altura, true);
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

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
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

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(img, getPos_X(), getPos_Y(), null);
    }
}
