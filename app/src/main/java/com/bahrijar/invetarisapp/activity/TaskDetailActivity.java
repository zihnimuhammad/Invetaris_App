package com.bahrijar.invetarisapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bahrijar.invetarisapp.R;

public class TaskDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task_assigned);

        btn_back = findViewById(R.id.btn_back_detail);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_detail:
                Intent backtoListTask = new Intent(TaskDetailActivity.this, TaskAssignedActivity.class);
                backtoListTask.addCategory(Intent.CATEGORY_LAUNCHER);
                backtoListTask.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backtoListTask);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}