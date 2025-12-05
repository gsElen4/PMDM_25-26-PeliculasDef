package com.elena.peliculasdef;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorPelicula extends RecyclerView.Adapter<AdaptadorPelicula.CeldaPeliculasJava> {
    ArrayList<Pelicula> pelicula;
    List<Pelicula> datos;

    int selectedPos = RecyclerView.NO_POSITION;

    public AdaptadorPelicula(List<Pelicula> peliculas){
        this.pelicula = new ArrayList<>(peliculas);
    }

    @NonNull
    @Override
    public CeldaPeliculasJava onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CeldaPeliculasJava(LayoutInflater.from(parent.getContext()).inflate(R.layout.celdapeli, parent, false));
    }

    //Metodo de la pulsación
    public int getSelectedPos() {
        return selectedPos;
    }
    public void setSelectedPos(int nuevaPos){
        if(nuevaPos == this.selectedPos){ //Si se pulsa el elemento marcado
            this.selectedPos =RecyclerView.NO_POSITION; //Se establece que no hay una posición marcada
            notifyItemChanged(nuevaPos); //notifica al adaptador que desmarque esa posición
        } else { //cuando el elemanto no esté marcado
            if(this.selectedPos >=0){ //si ya hay una posición marcada
                notifyItemChanged(this.selectedPos); //se desmarca
            }
            this.selectedPos = nuevaPos; //se actualiza la nueva posicion a la posicion pulsada
            notifyItemChanged(nuevaPos); //se marca la nueva posicion

        }
    }

    @Override
    public void onBindViewHolder(@NonNull CeldaPeliculasJava holder, int position) {
        Pelicula peli = pelicula.get(position);
        holder.foto.setImageResource(peli.getPortada());
        holder.titulo.setText(peli.getTitulo());
        holder.director.setText(peli.getDirector());
        holder.fotoclase.setImageResource(peli.getClasi());

        if(selectedPos == position){
            holder.itemView.setBackgroundResource(R.color.seleccionado);
        } else {
            holder.itemView.setBackgroundResource(R.color.colorcelda);
        }
    }

    @Override
    public int getItemCount() {
        return pelicula.size();
    }


    public class CeldaPeliculasJava extends RecyclerView.ViewHolder{
        TextView titulo, director;
        ImageView foto, fotoclase;

        public CeldaPeliculasJava(@NonNull View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.tVTitulo);
            this.director = itemView.findViewById(R.id.tVDirector);
            this.foto = itemView.findViewById(R.id.iVFoto);
            this.fotoclase = itemView.findViewById(R.id.iVClase);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posPulsada = getAbsoluteAdapterPosition(); //devuelve la posición del view en el adaptador
                setSelectedPos(posPulsada);

                if (selectedPos > RecyclerView.NO_POSITION){
                    Toast.makeText(itemView.getContext(), pelicula.get(posPulsada).getTitulo(), Toast.LENGTH_SHORT).show();
                    MainActivity main = (MainActivity) itemView.getContext();
                    TextView tv = main.findViewById(R.id.tVTitBarr);
                    tv.setText(pelicula.get(posPulsada).getTitulo());
                }
            }
        });
        }
    }
}
