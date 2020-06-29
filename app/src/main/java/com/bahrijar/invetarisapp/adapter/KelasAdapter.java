package com.bahrijar.invetarisapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.activity.DetailKelasActivity;
import com.bahrijar.invetarisapp.activity.EmptyRecyclerView;
import com.bahrijar.invetarisapp.activity.MainActivity;
import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.model.Kelas;


import java.util.ArrayList;
import java.util.List;

public class KelasAdapter extends EmptyRecyclerView.Adapter<KelasAdapter.ViewHolder> {

    List<Kelas> listKelas;
    Context context;

    public KelasAdapter(Context context, List<Kelas> listKelas) {
        this.listKelas = listKelas;
        this.context = context;

    }


    public void setListKelas(List<Kelas> listKelas) {
        this.listKelas = listKelas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kelas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvNamaKelas.setText(listKelas.get(position).getNamaKelas());
        holder.tvLokasi.setText(listKelas.get(position).getLokasi());
        holder.tvKapasitas.setText(listKelas.get(position).getKapasitas());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Kelas kelas = listKelas.get(position);
//                Intent i = new Intent(view.getContext(), DetailActivity.class);
//                i.putExtra("EXTRA_KELAS", kelas);
////            i.putExtra("id", kelas.getId());
//                view.getContext().startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (listKelas != null) {
            return listKelas.size();
        }
        return 0;
    }

    public class ViewHolder extends EmptyRecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNamaKelas, tvLokasi, tvKapasitas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaKelas = itemView.findViewById(R.id.txt_name_kelas);
            tvLokasi = itemView.findViewById(R.id.txt_location_task);
            tvKapasitas = itemView.findViewById(R.id.txt_capacity_kelas);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Kelas kelas = listKelas.get(position);
            ArrayList<Barang> barang = kelas.getBarang();
            Intent i = new Intent(view.getContext(), DetailKelasActivity.class);
            i.putExtra(DetailKelasActivity.EXTRA_KELAS, kelas);
            i.putParcelableArrayListExtra(DetailKelasActivity.EXTRA_BARANG, barang);
            view.getContext().startActivity(i);
        }
    }
}
