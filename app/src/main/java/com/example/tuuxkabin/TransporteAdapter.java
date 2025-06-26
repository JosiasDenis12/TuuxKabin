package com.example.tuuxkabin;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class TransporteAdapter extends RecyclerView.Adapter<TransporteAdapter.ViewHolder> {
    private List<Transporte> transporteList;

    public TransporteAdapter(List<Transporte> transporteList) {
        this.transporteList = transporteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transporte, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transporte transporte = transporteList.get(position);

        holder.tvNombreServicio.setText(transporte.getNombre());
        holder.ratingBar.setRating(transporte.getRating());

        // Cambiar los colores de las estrellas según el rating
        if (transporte.getRating() >= 1) {
            holder.ratingBar.setProgressTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.getContext(), R.color.star_filled)));
            holder.ratingBar.setSecondaryProgressTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.getContext(), R.color.star_empty)));
        } else {
            holder.ratingBar.setProgressTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.getContext(), R.color.star_empty)));
            holder.ratingBar.setSecondaryProgressTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.getContext(), R.color.star_empty)));
        }

        holder.tvRating.setText(String.format(Locale.getDefault(),
                "%.1f (%d reseñas)", transporte.getRating(), transporte.getNumResenas()));
        holder.tvHorario.setText(transporte.getHorario());

        try {
            holder.ivTransporte.setImageResource(transporte.getImagenResId());
        } catch (Resources.NotFoundException e) {
            holder.ivTransporte.setImageResource(R.drawable.ic_google2);
        }

        holder.btnLlamar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + transporte.getNumeroTelefono()));
            holder.itemView.getContext().startActivity(intent);
        });

        // 👉 Abrir DetalleTaxiActivity al hacer clic en el item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetalleTaxiActivity.class);
            intent.putExtra("transporte", transporte); // Debes asegurarte que Transporte implemente Serializable o Parcelable
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return transporteList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivTransporte;
        final TextView tvNombreServicio;
        final RatingBar ratingBar;
        final TextView tvRating;
        final TextView tvHorario;
        final Button btnLlamar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTransporte = itemView.findViewById(R.id.ivTransporte);
            tvNombreServicio = itemView.findViewById(R.id.tvNombreServicio);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvHorario = itemView.findViewById(R.id.tvHorario);
            btnLlamar = itemView.findViewById(R.id.btnLlamar);
        }
    }

    public void setFilteredList(List<Transporte> filteredList) {
        this.transporteList = filteredList;
        notifyDataSetChanged();
    }

}
