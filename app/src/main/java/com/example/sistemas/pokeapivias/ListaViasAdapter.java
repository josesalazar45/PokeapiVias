package com.example.sistemas.pokeapivias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemas.pokeapivias.models.ViasRespuesta;

import java.util.ArrayList;


public class ListaViasAdapter extends RecyclerView.Adapter<ListaViasAdapter.ViewHolder>
{
    private ArrayList<ViasRespuesta> dataset;
    private Context context;

    public ListaViasAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vias, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViasRespuesta p = dataset.get(position);
        holder.nombreTextView.setText(p.getCodigo());
        holder.textView6.setText(p.getIdentificador());
        holder.textView11.setText(p.getLongitudafirmado());
        holder.textView13.setText(p.getLongitudpavimento());

        holder.textView15.setText(p.getLongitudvia());
        holder.textView17.setText(p.getMuncipio());
        holder.textView19.setText(p.getNombrevia());


        //Glide.with(context)
        //      .load("http://pokeapi.co/media/sprites/pokemon/" + p.getNumber() + ".png")
        //    .centerCrop()
        //  .crossFade()
        //.diskCacheStrategy(DiskCacheStrategy.ALL)
        //.into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<ViasRespuesta> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //private ImageView fotoImageView;
        private TextView nombreTextView;
        private TextView textView6;
        private TextView textView11;
        private TextView textView13;
        private TextView textView15;
        private TextView textView17;
        private TextView textView19;

        public ViewHolder(View itemView) {
            super(itemView);

            //fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            textView6 = (TextView) itemView.findViewById(R.id.textView6);
            textView11 = (TextView) itemView.findViewById(R.id.textView11);
            textView13 = (TextView) itemView.findViewById(R.id.textView13);
            textView15 = (TextView) itemView.findViewById(R.id.textView15);
            textView17 = (TextView) itemView.findViewById(R.id.textView17);
            textView19 = (TextView) itemView.findViewById(R.id.textView19);
        }
    }



}
