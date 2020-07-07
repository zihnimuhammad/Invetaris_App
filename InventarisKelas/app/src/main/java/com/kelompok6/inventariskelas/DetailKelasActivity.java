package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailKelasActivity extends AppCompatActivity {

    ImageView fotoKelas;
    TextView namaKelas, lokasiKelas;
    Button btnLapor;
    ListView lvBarang;
    SimpleAdapter adapter;

    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> listBarang;
    String[] nmBarang;
    String[] jnsBarang;
    String[] jmlBarang;
    String[] icBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kelas);

        fotoKelas = (ImageView) findViewById(R.id.foto_kelas);
        namaKelas = (TextView) findViewById(R.id.nama_kelas);
        lokasiKelas = (TextView) findViewById(R.id.lokasi_kelas);
        lvBarang = (ListView) findViewById(R.id.list_barang);
        btnLapor = (Button) findViewById(R.id.btn_lapor);

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

        adapter = new SimpleAdapter(this, listBarang, R.layout.item_list_barang,
                new String[]{"Nama Barang", "Jenis Barang", "Jumlah Barang","Gambar Barang"}, new int[]{R.id.nama_barang,(R.id.jenismerk_barang),(R.id.jumlah_barang),
                (R.id.icon_barang)});
        lvBarang.setAdapter(adapter);

        btnLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toBuatLaporan = new Intent(DetailKelasActivity.this, BuatLaporanActivity.class);
                startActivity(toBuatLaporan);
            }
        });

    }

    public void backtoHome(View view) {
        Intent backtoHome = new Intent(DetailKelasActivity.this, DaftarKelasActivity.class);
        backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
        backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backtoHome);
    }
}
