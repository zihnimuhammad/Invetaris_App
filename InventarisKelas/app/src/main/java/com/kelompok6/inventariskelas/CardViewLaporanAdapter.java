package com.kelompok6.inventariskelas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewLaporanAdapter extends RecyclerView.Adapter<CardViewLaporanAdapter.CardViewHolder> {
    @NonNull
    @Override
    public CardViewLaporanAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_laporan_masuk, viewGroup, false);
        return new CardViewLaporanAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewLaporanAdapter.CardViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class CardViewHolder extends RecyclerView.ViewHolder {


        public CardViewHolder(final View itemView) {
            super(itemView);
        }
    }
}
