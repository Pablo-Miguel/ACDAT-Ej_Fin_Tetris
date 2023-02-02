package com.example.acdat_ej_fin_tetris.threads;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.acdat_ej_fin_tetris.R;

public class MusicThread extends Thread {

    private Context context;
    private Boolean running;
    private MediaPlayer mediaPlayer;

    public MusicThread(Context context) {
        this.context = context;
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        mediaPlayer = MediaPlayer.create(context, R.raw.nivel1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}
