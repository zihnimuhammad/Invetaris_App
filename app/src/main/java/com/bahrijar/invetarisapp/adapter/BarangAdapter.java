package com.bahrijar.invetarisapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.model.Barang;


import java.util.ArrayList;
import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {

    Context context;
    private List<Barang> listBarang;

    public BarangAdapter(Context context, List<Barang> listBarang) {
        this.context = context;
        this.listBarang = listBarang;
    }

    public void setListBarang(List<Barang> listBarang) {
        this.listBarang = listBarang;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNamaBarang.setText(listBarang.get(position).getNamabarang());
        holder.tvJumlah.setText(listBarang.get(position).getJumlah());

    }

    @Override
    public int getItemCount() {
            return listBarang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaBarang, tvJumlah;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.nama_barang);
            tvJumlah = itemView.findViewById(R.id.jumlah_barang);

        }


    }
}