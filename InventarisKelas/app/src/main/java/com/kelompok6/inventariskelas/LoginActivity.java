package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class LoginActivity extends AppCompatActivity {

    Animation bottom_to_top, login;
    ConstraintLayout form_login;
    Button vBtnLogin;
    EditText vNip, vPassword;

    ImageView iv_login;
    Spinner spinner_role;

    String iUsernip, iUserpassword;
    Handler handler;
    Runnable runnable;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        form_login = findViewById(R.id.form_login);
        vBtnLogin = findViewById(R.id.btn_login);
        vNip = findViewById(R.id.edt_nip);
        vPassword = findViewById(R.id.edt_password);
        spinner_role=findViewById(R.id.spinner_role);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        spinner_role.setAdapter(adapter);

        // Load Animations
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        login = AnimationUtils.loadAnimation(this, R.anim.login);

        // Load Views
        iv_login = findViewById(R.id.iv_login);

        // Run Animasi
        iv_login.startAnimation(login);

        Fade fade = new Fade();
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.background, true);
        getWindow();


        // Run Animasi
        form_login.startAnimation(bottom_to_top);

        // Onclick Button
        vBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iUsernip = vNip.getText().toString();
                iUserpassword = vPassword.getText().toString();
                String role = spinner_role.getSelectedItem().toString();

                if (iUsernip.equals("mhs")&& iUserpassword.equals("mhs")&& role.equals("mahasiswa")) {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("data_role", role);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (iUsernip.equals("kajur")&& iUserpassword.equals("kajur")&& role.equals("kajur")) {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, KajurActivity.class);
                    intent.putExtra("data_role", role);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (iUsernip.equals("")){
                    vNip.setError("Masukkan Nomor induk Anda");
                    vNip.requestFocus();
                } else if (iUserpassword.equals("")) {
                    vPassword.setError("Masukkan Password Anda");
                    vPassword.requestFocus();
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
