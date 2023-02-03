package com.example.acdat_ej_fin_tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.acdat_ej_fin_tetris.pojos.Tetris;
import com.example.acdat_ej_fin_tetris.surfaceviews.TetrisGameView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Integer level = (Integer) getIntent().getSerializableExtra("level");
        setContentView(new TetrisGameView(GameActivity.this, this, level));
    }

    public void loseActivity(Integer level, Integer puntos) {
        Intent intent = new Intent(GameActivity.this, LoseActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("level", level);
        intent.putExtra("puntos", puntos);
        startActivity(intent);
        this.finish();
    }

    public void onBackPressed() {
        Toast t = Toast.makeText(this, "No se puede volver", Toast.LENGTH_SHORT);
        t.show();
    }
}