package com.bahrijar.invetarisapp.fragment.mahasiswa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.activity.DaftarKelasActivity;
import com.bahrijar.invetarisapp.activity.EmptyRecyclerView;
import com.bahrijar.invetarisapp.adapter.TaskFinishedAdapter;
import com.bahrijar.invetarisapp.model.Task;
import com.bahrijar.invetarisapp.network.service.ApiInterface;
import com.bahrijar.invetarisapp.utils.SharedPrefManager;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener{
    SharedPrefManager sharedPrefManager;
    private Handler mHandler;

    private ImageButton btnDaftarKelas, btnPinjamBarang;
    private TextView tvName, tvRole;

    private ArrayList<Task> listTask = new ArrayList<>();
    private TaskFinishedAdapter mAdapter;
    EmptyRecyclerView rvData;
    ApiInterface apiInterface;

    private Runnable updateAdapterRunnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.setTask(listTask);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_mhs, container, false);
//        rvData = view.findViewById(R.id.rv_newtaskfinished);
//
//        rvData.setEmptyView(view.findViewById(R.id.empty_view));
        sharedPrefManager = new SharedPrefManager(view.getContext());

        btnDaftarKelas = view.findViewById(R.id.btn_daftar_kelas);
        btnPinjamBarang = view.findViewById(R.id.btn_pinjam_barang);

        tvName = view.findViewById(R.id.tv_name);
        tvRole = view.findViewById(R.id.tv_role);

        // Set Text pake shared preferences
        tvName.setText(sharedPrefManager.getSPNama());
        tvRole.setText(sharedPrefManager.getSPRole());


        mAdapter = new TaskFinishedAdapter(listTask);

        // Add Click Listener
        btnDaftarKelas.setOnClickListener(this);
        btnPinjamBarang.setOnClickListener(this);


        mHandler = new Handler();
//        showRecyclerNewTaskFinished();
        return view;
    }

//    private void showRecyclerNewTaskFinished() {
//        rvData.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        rvData.setItemAnimator(new DefaultItemAnimator());
//        rvData.setAdapter(mAdapter);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_daftar_kelas:
                sharedPrefManager.getSPToken();
                startActivity(new Intent(getActivity(), com.bahrijar.invetarisapp.activity.mahasiswa.DaftarKelasActivity.class));
                break;
            case R.id.btn_pinjam_barang:
                Intent j = new Intent(getActivity(), DaftarKelasActivity.class);
                startActivity(j);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        mHandler.postDelayed(updateAdapterRunnable, 0);
    }
    @Override
    public void onPause() {
        super.onPause();

        mHandler.removeCallbacks(updateAdapterRunnable);
    }

}
