package com.elena.peliculasdef;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdaptadorInfoPeli extends RecyclerView.Adapter<AdaptadorInfoPeli.CeldaPeliculasJava> {
    ArrayList<Pelicula> pelicula;

    int selectedPos = RecyclerView.NO_POSITION;

    public AdaptadorInfoPeli(List<Pelicula> peliculas){
        this.pelicula = new ArrayList<>(peliculas);
    }

    @NonNull
    @Override
    public CeldaPeliculasJava onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CeldaPeliculasJava(LayoutInflater.from(parent.getContext()).inflate(R.layout.celdapeli, parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull CeldaPeliculasJava holder, int position) {
        Pelicula peli = pelicula.get(position);
        holder.foto.setImageResource(peli.getPortada());
        holder.titulo.setText(peli.getTitulo());
        holder.director.setText(peli.getDirector());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        holder.fecha.setText(df.format(peli.getFecha()));

        holder.duracion.setText(peli.getDuracion());
        holder.sala.setText(peli.getSala());
        holder.fotoclase.setImageResource(peli.getClasi());

    }

    @Override
    public int getItemCount() {
        return pelicula.size();
    }


    public class CeldaPeliculasJava extends RecyclerView.ViewHolder{
        TextView titulo, director, fecha, duracion, sala;
        ImageView foto, fotoclase;

        public CeldaPeliculasJava(@NonNull View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.tVTitulo);
            this.director = itemView.findViewById(R.id.tVDirector);
            this.fecha = itemView.findViewById(R.id.tVFecha);
            this.duracion = itemView.findViewById(R.id.tVDurac);
            this.sala = itemView.findViewById(R.id.tVSala);
            this.foto = itemView.findViewById(R.id.iVFoto);
            this.fotoclase = itemView.findViewById(R.id.iVClase);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}


