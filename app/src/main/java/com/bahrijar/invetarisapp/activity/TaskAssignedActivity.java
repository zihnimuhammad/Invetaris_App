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

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.adapter.TaskAssignedAdapter;
import com.bahrijar.invetarisapp.model.Task;

import java.util.ArrayList;


public class TaskAssignedActivity extends AppCompatActivity implements View.OnClickListener {



    private Handler mHandler;

    ImageButton btn_back;
    private ArrayList<Task>listTask = new ArrayList<>();
    private TaskAssignedAdapter mAdapter;
    EmptyRecyclerView recyclerView;

    private Runnable updateAdapterRunnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.setTask(listTask);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assigned);

        mAdapter = new TaskAssignedAdapter(listTask);

        recyclerView = findViewById(R.id.rv_task_assigned);
        recyclerView.setEmptyView(findViewById(R.id.empty_view));

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        mHandler = new Handler();


        showRecyclerNewTaskFinished();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                Intent backtoHome = new Intent(TaskAssignedActivity.this, MainActivity.class);
                backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
                backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backtoHome);
                break;
//            case R.id.btn_detail:
//                Intent gotoDetail = new Intent(TaskAssignedActivity.this, TaskDetailActivity.class);
//                startActivity(gotoDetail);
        }
    }

    private void showRecyclerNewTaskFinished() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    protected void onResume() {
        super.onResume();

        mHandler.postDelayed(updateAdapterRunnable, 0);
    }
    @Override
    protected void onPause() {
        super.onPause();

        mHandler.removeCallbacks(updateAdapterRunnable);
    }



}
