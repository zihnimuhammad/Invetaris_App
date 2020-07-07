package com.kelompok6.inventariskelas;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailLaporanActivity extends AppCompatActivity {
    TextView lokasiKelas, kodeLaporan;
    ImageView fotoKerusakan1, fotoKerusakan2, fotoKerusakan3;
    ListView lvDetailLaporan;
    SimpleAdapter adapter;

    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> listBarang;
    String[] nmBarang;
    String[] jnsBarang;
    String[] jmlBarang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        lokasiKelas = (TextView) findViewById(R.id.lokasi_kelas);
        kodeLaporan = (TextView) findViewById(R.id.kode_laporan);
        fotoKerusakan1 = (ImageView) findViewById(R.id.foto_kerusakan1);
        fotoKerusakan2 = (ImageView) findViewById(R.id.foto_kerusakan2);
        fotoKerusakan3 = (ImageView) findViewById(R.id.foto_kerusakan3);
        lvDetailLaporan = (ListView) findViewById(R.id.lv_detail_laporan);

        nmBarang = new String[] {
                "Meja","Kursi"
        };
        jnsBarang = new String[]{
                "DMB-02","DCB-02"
        };
        jmlBarang = new String[]{
                "2","3"
        };
        listBarang = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<nmBarang.length; i++){
            map = new HashMap<String, String>();
            map.put("Nama Barang", nmBarang[i]);
            map.put("Jenis Barang", jnsBarang[i]);
            map.put("Jumlah Barang", jmlBarang[i]);
            listBarang.add(map);
        }

        adapter = new SimpleAdapter(this, listBarang, R.layout.item_detail_pinjaman,
                new String[]{"Nama Barang", "Jenis Barang", "Jumlah Barang"}, new int[]{R.id.nama_barang,(R.id.jenismerk_barang),(R.id.jumlah_barang)});
        lvDetailLaporan.setAdapter(adapter);
    }

    public void cancel(View view) {
        finish();
    }
}
