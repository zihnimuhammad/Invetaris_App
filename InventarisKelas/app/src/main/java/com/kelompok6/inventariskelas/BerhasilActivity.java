package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BerhasilActivity extends AppCompatActivity {
    TextView judulNotif, isiNotif, hasilNotif;
    Button btnSelesai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil);

        judulNotif = (TextView) findViewById(R.id.judul_notif);
        isiNotif = (TextView) findViewById(R.id.isi_notif);
        hasilNotif = (TextView) findViewById(R.id.hasi_notif);
        btnSelesai = (Button)findViewById(R.id.btn_selesai);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoHome = new Intent(BerhasilActivity.this, MainActivity.class);
                backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
                backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backtoHome);
            }
        });

    }
}
