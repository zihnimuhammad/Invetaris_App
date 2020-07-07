package com.bahrijar.invetarisapp.activity.mahasiswa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.utils.SharedPrefManager;

public class LaporanBerhasilActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    TextView vIdLaporan;
    Button vBtnSelesai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_berhasil);
        sharedPrefManager = new SharedPrefManager(this);

        vIdLaporan = findViewById(R.id.id_laporan);
        vBtnSelesai = findViewById(R.id.btn_selesai);

        vIdLaporan.setText(sharedPrefManager.getSpLaporan());

        vBtnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoHome = new Intent(LaporanBerhasilActivity.this, MainActivity.class);
                backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
                backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backtoHome);
            }
        });
    }
}
