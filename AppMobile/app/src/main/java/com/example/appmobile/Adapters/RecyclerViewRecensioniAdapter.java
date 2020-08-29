package com.example.appmobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobile.R;

import java.util.List;

public class RecyclerViewRecensioniAdapter extends RecyclerView.Adapter<RecyclerViewRecensioniAdapter.ViewHolder> {
    float valutazioni[];
    Context context;
    private List<String> nomiRecensori;
    private List<String> testiRecensioni;

    public RecyclerViewRecensioniAdapter(Context context, List<String> nomiRecensori, List<String> testiRecensioni, float valutazioni[]) {
        this.nomiRecensori = nomiRecensori;
        this.testiRecensioni = testiRecensioni;
        this.valutazioni = valutazioni;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_recensioni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nomeRecensore.setText(nomiRecensori.get(position));
        holder.valutazione.setRating(valutazioni[position]);
        holder.testoRecensione.setText(testiRecensioni.get(position));
    }

    public void setNameToShow(String nameToShow, int position) {
        nomiRecensori.set(position, nameToShow);

    }

    @Override
    public int getItemCount() {
        return testiRecensioni.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeRecensore;
        RatingBar valutazione;
        ScrollView recensione;
        TextView testoRecensione;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeRecensore = itemView.findViewById(R.id.recyclerViewNomeRecensore);
            valutazione = itemView.findViewById(R.id.recyclerViewRatingBar);
            recensione = itemView.findViewById(R.id.recyclerViewTestoRecensione);
            testoRecensione = itemView.findViewById(R.id.testoRecensioneAdapter);
        }

    }
}
