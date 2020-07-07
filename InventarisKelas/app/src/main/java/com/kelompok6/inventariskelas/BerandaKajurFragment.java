package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BerandaKajurFragment extends Fragment {

    TextView namaUser, jenisUser;
    ImageButton btnLaporanMsk, btnPenugasan;
    ImageView fotoUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.frag_beranda_kajur, container, false);

        fotoUser = (ImageView) fragmentView.findViewById(R.id.img_user);
        namaUser = (TextView) fragmentView.findViewById(R.id.tv_name_user);
        jenisUser = (TextView) fragmentView.findViewById(R.id.tv_role_user);
        btnLaporanMsk = (ImageButton) fragmentView.findViewById(R.id.btn_laporan_masuk);
        btnPenugasan = (ImageButton) fragmentView.findViewById(R.id.btn_penugasan);

        btnLaporanMsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLaporanMasuk = new Intent(getActivity(), LaporanMasukActivity.class);
                startActivity(toLaporanMasuk);
            }
        });

        btnPenugasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLaporanDitugaskan = new Intent(getActivity(),LaporanDitugaskanActivity.class);
                startActivity(toLaporanDitugaskan);
            }
        });

        return fragmentView;
    }
}
