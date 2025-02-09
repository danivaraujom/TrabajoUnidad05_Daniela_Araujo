package com.carba.trabajounidad05_daniela_araujo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MultimediaAdapter extends RecyclerView.Adapter<MultimediaAdapter.MultimediaViewHolder> {

    private Context context;
    private List<MultimediaItem> multimediaItems;

    public MultimediaAdapter(Context context, List<MultimediaItem> multimediaItems) {
        this.context = context;
        this.multimediaItems = multimediaItems;
    }

    @NonNull
    @Override
    public MultimediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_multimedia, parent, false);
        return new MultimediaViewHolder(itemView); // Crea y retorna el ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull MultimediaViewHolder holder, int position) {
        MultimediaItem item = multimediaItems.get(position); // Obtengo el ítem correspondiente
        holder.textViewTitulo.setText(item.getTitulo());
        holder.textViewDescripcion.setText(item.getDescripcion());
        holder.imageView.setImageResource(item.getImagen());

        // Configuro la acción al hacer click en el ítem
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleActivity.class); // Lanza la actividad de detalle
            intent.putExtra("item", item); // Pasa el ítem multimedia
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return multimediaItems.size(); // Devuelvo el número de ítems
    }

    public static class MultimediaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitulo;
        TextView textViewDescripcion;
        ImageView imageView;

        public MultimediaViewHolder(View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.textTitulo);
            textViewDescripcion = itemView.findViewById(R.id.textDescripcion);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

