package com.example.acdat_ej_fin_tetris.recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.acdat_ej_fin_tetris.databinding.ViewTetrisBinding;
import com.example.acdat_ej_fin_tetris.pojos.Partida;
import com.example.acdat_ej_fin_tetris.pojos.Tetris;

import java.util.ArrayList;

public class TetrisRecycler extends RecyclerView.Adapter<TetrisRecycler.ViewHolder> implements View.OnClickListener {

    private ArrayList<Partida> listaPartida;
    private View.OnClickListener listener;

    public TetrisRecycler(ArrayList<Partida> listaPartida) {
        this.listaPartida = listaPartida;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewTetrisBinding binding = ViewTetrisBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        binding.getRoot().setOnClickListener(this);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(listaPartida.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPartida.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (this.listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewTetrisBinding binding;
        public ViewHolder(ViewTetrisBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Partida partida){
            binding.lblName.setText(partida.getName());
            binding.lblScore.setText("Score: " + partida.getScore());
        }

    }
}
