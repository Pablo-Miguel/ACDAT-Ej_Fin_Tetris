package com.example.acdat_ej_fin_tetris.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ButtonMenu extends Menu {

    private Bitmap img;
    private int base, altura;

    public ButtonMenu(Integer id, float pos_X, float pos_Y, int color, Resources resources, int img_ref, int base, int altura) {
        super(id, pos_X, pos_Y, color);

        this.base = base;
        this.altura = altura;
        Bitmap img = BitmapFactory.decodeResource(resources, img_ref);
        this.img = img.createScaledBitmap(img, base, altura, true);
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

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(img, getPos_X(), getPos_Y(), null);
    }

    @Override
    public boolean isTouched(int coord_X, int coord_y) {
        if(coord_X > super.getPos_X() && coord_X < super.getPos_X() + this.getBase() && coord_y > super.getPos_Y() && coord_y < super.getPos_Y() + this.getAltura()){
            return true;
        }
        return false;
    }

}
