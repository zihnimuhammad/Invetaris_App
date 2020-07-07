package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailPinjamanActivity extends AppCompatActivity {

    TextView kodePinjaman, tglPeminjaman;
    ListView lvDetailPinjaman;
    SimpleAdapter adapter;

    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> listBarang;
    String[] nmBarang;
    String[] jnsBarang;
    String[] jmlBarang;
    String[] icBarang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pinjaman);

        kodePinjaman = (TextView) findViewById(R.id.kode_peminjaman);
        tglPeminjaman = (TextView) findViewById(R.id.tgl_peminjaman);
        lvDetailPinjaman = (ListView) findViewById(R.id.lv_detail_pinjaman);

        nmBarang = new String[] {
                "Meja","Kursi","White Board","Meja Dosen","Kursi Dosen"
        };
        jnsBarang = new String[]{
                "DMB-02","DCB-02","Sakura","Lion 106 N","Vortex V Series"
        };
        jmlBarang = new String[]{
                "30","30","1","1","1"
        };
        icBarang = new String[]{
                Integer.toString(R.drawable.ic_meja),Integer.toString(R.drawable.ic_kursi),Integer.toString(R.drawable.ic_whiteboard),
                Integer.toString(R.drawable.ic_meja),Integer.toString(R.drawable.ic_kursi)
        };
        listBarang = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<nmBarang.length; i++){
            map = new HashMap<String, String>();
            map.put("Nama Barang", nmBarang[i]);
            map.put("Jenis Barang", jnsBarang[i]);
            map.put("Jumlah Barang", jmlBarang[i]);
            map.put("Gambar Barang", icBarang[i]);
            listBarang.add(map);
        }

        adapter = new SimpleAdapter(this, listBarang, R.layout.item_detail_pinjaman,
                new String[]{"Nama Barang", "Jenis Barang", "Jumlah Barang","Gambar Barang"}, new int[]{R.id.nama_barang,(R.id.jenismerk_barang),(R.id.jumlah_barang),
                (R.id.icon_barang)});
        lvDetailPinjaman.setAdapter(adapter);
    }

    public void cancel(View view) {
        finish();
    }
}
