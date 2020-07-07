package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LaporanMasukActivity extends AppCompatActivity {

    private RecyclerView rvLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_masuk);

        rvLaporan = findViewById(R.id.rv_laporan_masuk);
        rvLaporan.setHasFixedSize(true);

        //list.addAll(DataKelas.getListData());
        //showRecyclerCardView();

    }

    public void backtoHome(View view) {
        Intent backtoHome = new Intent(LaporanMasukActivity.this, KajurActivity.class);
        backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
        backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backtoHome);
    }

}
