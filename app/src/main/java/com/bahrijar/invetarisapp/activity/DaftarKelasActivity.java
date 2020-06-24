package com.bahrijar.invetarisapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.adapter.KelasAdapter;
import com.bahrijar.invetarisapp.model.Kelas;
import com.bahrijar.invetarisapp.network.ServiceGenerator;
import com.bahrijar.invetarisapp.network.response.KelasResponse;
import com.bahrijar.invetarisapp.network.service.ApiInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKelasActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "DaftarKelasActivity";

    ImageButton btn_back;


    private KelasAdapter adapter;
    ApiInterface apiInterface;
    SwipeRefreshLayout swipeRefreshLayout;
    EmptyRecyclerView rvKelas;

    List<Kelas> kelasList = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelas);

        initViews();
        loadData();

    }

    private void initViews() {
        rvKelas = findViewById(R.id.rv_kelas);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        rvKelas.setEmptyView(findViewById(R.id.empty_view));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvKelas.setLayoutManager(layoutManager);
        adapter= new KelasAdapter(getApplicationContext(),kelasList);
        rvKelas.setAdapter(adapter);


        apiInterface = ServiceGenerator.createBaseService(this, ApiInterface.class);


        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    private void loadData() {
        apiInterface.getClasses().enqueue(new Callback<KelasResponse>() {
            @Override
            public void onResponse(Call<KelasResponse> call, Response<KelasResponse> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    kelasList = response.body().getKelas();
                    adapter.setListKelas(kelasList);

                }
            }

            @Override
            public void onFailure(Call<KelasResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                Intent backtoHome = new Intent(DaftarKelasActivity.this, MainActivity.class);
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
