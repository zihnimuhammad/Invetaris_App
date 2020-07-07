package com.kelompok6.inventariskelas;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewKelasAdapter extends RecyclerView.Adapter<CardViewKelasAdapter.CardViewViewHolder> {
    private ArrayList<Kelas> listKelas;

    CardViewKelasAdapter(ArrayList<Kelas> list) {
        this.listKelas = list;
    }

    @NonNull
    @Override
    public CardViewKelasAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_kelas, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewKelasAdapter.CardViewViewHolder holder, int position) {
        Kelas kelas = listKelas.get(position);

        holder.namaKelas.setText(kelas.getNama_kelas());
        holder.lokasiKelas.setText(kelas.getLokasi());
        holder.kapasitasKelas.setText(kelas.getKapasitas());

    }

    @Override
    public int getItemCount() {
        return listKelas.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView namaKelas, lokasiKelas, kapasitasKelas;
        CardView cvKelas;
        ImageView fotoKelas;

        CardViewViewHolder(final View itemView) {
            super(itemView);
            namaKelas = itemView.findViewById(R.id.nama_kelas);
            lokasiKelas = itemView.findViewById(R.id.lokasi_kelas);
            kapasitasKelas = itemView.findViewById(R.id.kapasitas_kelas);
            cvKelas = itemView.findViewById(R.id.cardview_kelas);

            cvKelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toDetailKelas = new Intent(view.getContext(), DetailKelasActivity.class);
                    view.getContext().startActivity(toDetailKelas);
                }
            });
        }
    }
}
