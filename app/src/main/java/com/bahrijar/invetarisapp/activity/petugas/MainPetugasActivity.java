package com.bahrijar.invetarisapp.activity.petugas;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.utils.SharedPrefManager;

public class MainPetugasActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_petugas);

        sharedPrefManager = new SharedPrefManager(this);
    }
}
