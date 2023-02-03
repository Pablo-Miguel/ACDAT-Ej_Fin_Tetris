package com.example.acdat_ej_fin_tetris.pojos;

import android.content.ContentValues;

import com.example.acdat_ej_fin_tetris.daos.dlls.PartidaEntry;

public class Partida {
    private String name;
    private Integer score;

    public Partida(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(PartidaEntry.NOMBRE, getName());
        values.put(PartidaEntry.SCORE, getScore());

        return values;
    }
}
