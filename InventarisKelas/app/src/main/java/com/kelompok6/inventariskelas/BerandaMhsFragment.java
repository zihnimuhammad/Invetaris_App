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

public class BerandaMhsFragment extends Fragment {

    TextView namaUser, jenisUser;
    ImageButton btnDaftarKelas, btnPinjamBarang;
    ImageView fotoUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.frag_beranda_mhs, container, false);

        fotoUser = (ImageView) fragmentView.findViewById(R.id.img_user);
        namaUser = (TextView) fragmentView.findViewById(R.id.tv_name_user);
        jenisUser = (TextView) fragmentView.findViewById(R.id.tv_role_user);
        btnDaftarKelas = (ImageButton) fragmentView.findViewById(R.id.btn_daftar_kelas);
        btnPinjamBarang = (ImageButton) fragmentView.findViewById(R.id.btn_pinjam_barang);

        btnDaftarKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDaftarKelas = new Intent(getActivity(), DaftarKelasActivity.class);
                startActivity(toDaftarKelas);
            }
        });

        btnPinjamBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPinjamBarang = new Intent(getActivity(),PinjamBarangActivity.class);
                startActivity(toPinjamBarang);
            }
        });

        return fragmentView;
    }

}
