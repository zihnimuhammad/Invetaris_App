package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class DaftarKelasActivity extends AppCompatActivity {

    private RecyclerView rvKelas;
    private ArrayList<Kelas> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelas);

        rvKelas = findViewById(R.id.rv_daftar_kelas);
        rvKelas.setHasFixedSize(true);

        list.addAll(DataKelas.getListData());
        showRecyclerCardView();

    }

    public void backtoHome(View view) {
        Intent backtoHome = new Intent(DaftarKelasActivity.this, MainActivity.class);
        backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
        backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backtoHome);
    }

    private void showRecyclerCardView() {
        rvKelas.setLayoutManager(new LinearLayoutManager(this));
        CardViewKelasAdapter cardViewKelasAdapter = new CardViewKelasAdapter(list);
        rvKelas.setAdapter(cardViewKelasAdapter);
    }


}
