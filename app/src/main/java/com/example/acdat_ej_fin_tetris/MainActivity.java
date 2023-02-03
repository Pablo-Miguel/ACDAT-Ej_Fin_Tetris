package com.example.acdat_ej_fin_tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.acdat_ej_fin_tetris.daos.DaoPartidas;
import com.example.acdat_ej_fin_tetris.daos.dlls.TetrisSQLiteHelper;
import com.example.acdat_ej_fin_tetris.databinding.ActivityMainBinding;
import com.example.acdat_ej_fin_tetris.pojos.Partida;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DaoPartidas.establecerConexion(new TetrisSQLiteHelper(MainActivity.this));

        binding.btnLvl1.setOnClickListener(MainActivity.this);
        binding.btnLvl2.setOnClickListener(MainActivity.this);
        binding.btnLvl3.setOnClickListener(MainActivity.this);
        binding.btnExit.setOnClickListener(MainActivity.this);
    }

    public void onBackPressed() {
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        Log.e("Tetris", "Click");
        if(v.getId() == R.id.btnLvl1){
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("level", 1);
            startActivity(intent);
            this.finish();
        } else if(v.getId() == R.id.btnLvl2){
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("level", 2);
            startActivity(intent);
            this.finish();
        } else if (v.getId() == R.id.btnLvl3){
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("level", 3);
            startActivity(intent);
            this.finish();
        } else {
            System.exit(0);
        }
    }
}