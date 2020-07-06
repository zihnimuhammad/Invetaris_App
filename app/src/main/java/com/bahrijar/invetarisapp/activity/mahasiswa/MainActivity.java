package com.bahrijar.invetarisapp.activity.mahasiswa;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.fragment.AccountFragment;
import com.bahrijar.invetarisapp.fragment.mahasiswa.HomeFragment;
import com.bahrijar.invetarisapp.utils.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mahasiswa);
        sharedPrefManager = new SharedPrefManager(this);

        BottomNavigationView mNavigation = findViewById(R.id.nav_view);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            mNavigation.setSelectedItemId(R.id.itemHome);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;

                    switch (item.getItemId()) {
                        case R.id.itemHome:
                            fragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                                    .commit();
                            return true;
                        case R.id.itemAccount:
                            fragment = new AccountFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                                    .commit();
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onPause() {
        super.onPause();
    }

}
