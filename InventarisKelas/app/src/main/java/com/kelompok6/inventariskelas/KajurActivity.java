package com.kelompok6.inventariskelas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KajurActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kajur);

        BottomNavigationView bottomNav = findViewById(R.id.nBottomNav);
        bottomNav.setOnNavigationItemSelectedListener(this);

        // kita set default nya Beranda
        loadFragment(new BerandaKajurFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }   return false;
    }

    // method listener untuk logika pemilihan
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;   switch (item.getItemId()){
            case R.id.beranda_menu:
                fragment = new BerandaKajurFragment();
                break;
            case R.id.akun_menu:
                fragment = new AkunFragment();
                break;
        }   return loadFragment(fragment);
    }
}