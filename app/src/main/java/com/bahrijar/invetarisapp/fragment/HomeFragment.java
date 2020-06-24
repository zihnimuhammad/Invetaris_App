package com.bahrijar.invetarisapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bahrijar.invetarisapp.activity.DaftarKelasActivity;
import com.bahrijar.invetarisapp.activity.EmptyRecyclerView;
import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.model.Task;
import com.bahrijar.invetarisapp.activity.TaskAssignedActivity;
import com.bahrijar.invetarisapp.TaskData;
import com.bahrijar.invetarisapp.adapter.TaskFinishedAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Handler mHandler;

    private CardView taskAssigned, taskFinished, vKelas;
    private ArrayList<Task> listTask = new ArrayList<>();
    private TaskFinishedAdapter mAdapter;
    EmptyRecyclerView rvData;

    private Runnable updateAdapterRunnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.setTask(listTask);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        rvData = view.findViewById(R.id.rv_newtaskfinished);
//
//        rvData.setEmptyView(view.findViewById(R.id.empty_view));

        taskAssigned = view.findViewById(R.id.cv_assigned);
        taskFinished = view.findViewById(R.id.cv_finished);
        vKelas = view.findViewById(R.id.cv_kelas);

        mAdapter = new TaskFinishedAdapter(listTask);

        // Add Click Listener
        taskAssigned.setOnClickListener(this);
        taskFinished.setOnClickListener(this);
        vKelas.setOnClickListener(this);

        mHandler = new Handler();

        //Add List Data Selesai terbaru
        listTask.addAll(TaskData.getListTaskData());
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
            case R.id.cv_assigned:
                Intent i = new Intent(getActivity(), TaskAssignedActivity.class);
                startActivity(i);
                break;
            case R.id.cv_kelas:
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
