package com.example.acdat_ej_fin_tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.acdat_ej_fin_tetris.daos.DaoPartidas;
import com.example.acdat_ej_fin_tetris.databinding.ActivityLoseBinding;
import com.example.acdat_ej_fin_tetris.pojos.Partida;
import com.example.acdat_ej_fin_tetris.pojos.Tetris;
import com.example.acdat_ej_fin_tetris.recyclers.TetrisRecycler;

import java.util.ArrayList;
import java.util.Collections;

public class LoseActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoseBinding binding;
    private Integer level, puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        binding = ActivityLoseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        level = (Integer) getIntent().getSerializableExtra("level");
        puntos = (Integer) getIntent().getSerializableExtra("puntos");

        binding.txtName.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        ArrayList<Partida> lista = DaoPartidas.getInstance().getPartidas();
        Collections.reverse(lista);
        binding.recycler.setAdapter(new TetrisRecycler(lista));

        binding.btnAdd.setOnClickListener(this);
        binding.btnNewGame.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        binding.txtName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnNewGame){
            if(binding.txtName.isEnabled()){
                DaoPartidas.getInstance().addPartida(new Partida("Unknown", puntos));
            }
            Intent intent = new Intent(LoseActivity.this, GameActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("level", level);
            startActivity(intent);
            this.finish();
        } else if(v.getId() == R.id.btnBack){
            if(binding.txtName.isEnabled()){
                DaoPartidas.getInstance().addPartida(new Partida("Unknown", puntos));
            }
            Intent intent = new Intent(LoseActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        } else if(v.getId() == R.id.btnAdd){
            if(!binding.txtName.getText().equals("")){
                DaoPartidas.getInstance().addPartida(new Partida(binding.txtName.getText().toString(), puntos));
                ArrayList<Partida> lista = DaoPartidas.getInstance().getPartidas();
                Collections.reverse(lista);
                binding.recycler.setAdapter(new TetrisRecycler(lista));
                binding.txtName.setEnabled(false);
                binding.btnAdd.setEnabled(false);
            }
        } else if(v.getId() == R.id.txtName){
            binding.txtName.setText("");
        }
    }

    public void onBackPressed() {
        Toast t = Toast.makeText(this, "No se puede volver", Toast.LENGTH_SHORT);
        t.show();
    }
}