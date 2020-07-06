package com.bahrijar.invetarisapp.activity.mahasiswa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.adapter.BarangAdapter;
import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.model.Kelas;
import com.bahrijar.invetarisapp.network.service.ApiInterface;

import java.util.ArrayList;

public class DetailKelasActivity extends AppCompatActivity implements View.OnClickListener{

    public static String EXTRA_KELAS = "extra_kelas";
    public static String EXTRA_BARANG = "extra_barang";
    public static String TAG = "detal activity";

    TextView tvKelas, tvLokasi;
    ImageButton btn_back;
    BarangAdapter adapter;
    ApiInterface apiInterface;

    int id;
    RecyclerView rvBarang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kelas);

        initViews();
//    loadData();

    }

    private void initViews() {
        tvKelas = findViewById(R.id.nama_kelas);
        tvLokasi = findViewById(R.id.lokasi_kelas);
        rvBarang= findViewById(R.id.rv_barang);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);


//        apiInterface = ServiceGenerator.createBaseService(this, ApiInterface.class);
//        id = kelas.getId();

        Intent i = getIntent();
        Kelas kelas = i.getParcelableExtra(EXTRA_KELAS);

        String namaKelas = kelas.getNamaKelas();
        String lokasi = kelas.getLokasi();
        tvKelas.setText(namaKelas);
        tvLokasi.setText(lokasi);

        ArrayList<Barang> barangList = i.getParcelableArrayListExtra(EXTRA_BARANG);
        assert barangList != null;
        Log.d(TAG, "Barang: " + barangList.get(0).getNamabarang());


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvBarang.setLayoutManager(layoutManager);
        adapter= new BarangAdapter(getApplicationContext(),barangList);
        adapter.setListBarang(barangList);
        rvBarang.setAdapter(adapter);

//
//        ArrayList<Barang> barangs = i.getParcelableArrayListExtra("EXTRA_BARANG");
//        barangs.addAll(barangList);


        }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                Intent backtoHome = new Intent(DetailKelasActivity.this, DaftarKelasActivity.class);
                backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
                backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backtoHome);
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
