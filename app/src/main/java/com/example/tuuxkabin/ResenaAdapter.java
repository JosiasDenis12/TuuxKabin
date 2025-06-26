package com.example.tuuxkabin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResenaAdapter extends RecyclerView.Adapter<ResenaAdapter.ResenaViewHolder> {

    private List<Resena> resenas;

    public ResenaAdapter(List<Resena> resenas) {
        this.resenas = resenas;
    }

    @NonNull
    @Override
    public ResenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resena, parent, false);
        return new ResenaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResenaViewHolder holder, int position) {
        Resena r = resenas.get(position);
        holder.tvUsuarioNombre.setText(r.getUsuario());
        holder.rbPuntuacion.setRating(r.getPuntuacion());
        holder.tvFecha.setText(r.getFecha());
        holder.tvComentario.setText(r.getComentario());
    }

    @Override
    public int getItemCount() {
        return resenas.size();
    }

    public static class ResenaViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsuarioNombre, tvFecha, tvComentario;
        RatingBar rbPuntuacion;

        public ResenaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsuarioNombre = itemView.findViewById(R.id.tvUsuarioNombre);
            rbPuntuacion = itemView.findViewById(R.id.rbPuntuacion);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvComentario = itemView.findViewById(R.id.tvComentario);
        }
    }
}
