package com.example.appmobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewFotoRecensioniAdapter extends RecyclerView.Adapter<RecyclerViewFotoRecensioniAdapter.FotoViewHolder> {

    Context context;
    private List<String> listaFoto;

    public RecyclerViewFotoRecensioniAdapter(Context context, List<String> listaFoto) {
        this.context = context;
        this.listaFoto = listaFoto;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.foto_view_holder, parent, false);
        return new FotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {

        Picasso.get().load(listaFoto.get(position)).resize(175, 151).into(holder.foto1);
    }

    @Override
    public int getItemCount() {
        return listaFoto.size();
    }

    public class FotoViewHolder extends RecyclerView.ViewHolder {

        ImageView foto1;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);
            foto1 = itemView.findViewById(R.id.fotoRecensioni1);
        }
    }
}
