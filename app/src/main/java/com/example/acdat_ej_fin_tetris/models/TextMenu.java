package com.example.acdat_ej_fin_tetris.models;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TextMenu extends Menu {

    private String text;
    private Paint paint;

    public TextMenu(float pos_X, float pos_Y, int color, String text) {
        super(pos_X, pos_Y, color);
        this.text = text;
        this.paint = getPaint();
        this.paint.setTextSize(50);
        this.paint.setFakeBoldText(true);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawText(text, getPos_X(), getPos_Y(), this.paint);
    }

    @Override
    public boolean isTouched(int coord_X, int coord_y) {
        return false;
    }

}
