package com.example.acdat_ej_fin_tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.acdat_ej_fin_tetris.surfaceviews.TetrisGameView;
import com.example.acdat_ej_fin_tetris.surfaceviews.TetrisView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(new TetrisView(MainActivity.this, this));
    }

    public void startGame(Integer level) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);

        intent.putExtra("level", level);

        startActivity(intent);
        this.finish();
    }

    public void onBackPressed() {
        System.exit(0);
    }
}