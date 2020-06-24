package com.bahrijar.invetarisapp.activity;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.adapter.BarangAdapter;
import com.bahrijar.invetarisapp.adapter.KelasAdapter;
import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.model.Kelas;
import com.bahrijar.invetarisapp.network.ServiceGenerator;
import com.bahrijar.invetarisapp.network.service.ApiInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        rvBarang= findViewById(R.id.rv_kelas);
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

        ArrayList<Barang> barang = i.getParcelableArrayListExtra(EXTRA_BARANG);
//        barang = kelas.getBarang();
        barang = kelas.getBarang();
        assert barang != null;
        Log.d(TAG, "Barang: " + barang.get(0).getNamabarang());




//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rvBarang.setLayoutManager(layoutManager);
//        adapter= new BarangAdapter(getApplicationContext(),barangList);
//        adapter.setListBarang(barangList);
//        rvBarang.setAdapter(adapter);



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

//    private void loadData() {
//        apiInterface.getidKelas(id).enqueue(new Callback<Barang>() {
//            @Override
//            public void onResponse(@NotNull Call<Barang> call, @NotNull Response<Barang> response) {
//                if (response.code() == 200) {
//                    List<Barang> barang = response.body().getBarang();
//                    kelas.setBarang(barang);
//
//                    final ArrayList<Barang> barangList = getIntent().getParcelableArrayListExtra("list");
//
//                    for (int j =0; j<barangList.size(); j++) {
//                        //String namaBarang = barangList.get(j).getNamabarang();
//                        adapter = new BarangAdapter((ArrayList<Barang>) barangList);
//                        rvBarang.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//
////                        updateAdapterRunnable = new Runnable() {
////                            @Override
////                            public void run() {
////                                adapter.setListBarang((ArrayList<Kelas>) barangList);
////                            }
////                        };
//                    }
//                    //Toast.makeText(getApplicationContext(), "Barang: " + barangList.get(0).getNamabarang(), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Barang> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
