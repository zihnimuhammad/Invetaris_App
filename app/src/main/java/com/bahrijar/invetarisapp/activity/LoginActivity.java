package com.bahrijar.invetarisapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    Animation bottom_to_top;
    ConstraintLayout form_login;
    Button vBtnLogin;
    private TextInputEditText vNip, vPassword;

    private TextInputLayout txtLayoutNip, txtLayoutPassword;

    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init() {
        form_login = findViewById(R.id.form_login);
        vBtnLogin = findViewById(R.id.btn_login);
        vNip = findViewById(R.id.edt_nip);
        vPassword = findViewById(R.id.edt_password);
        txtLayoutNip = findViewById(R.id.txt_layout_nip);
        txtLayoutPassword = findViewById(R.id.txt_layout_password);

        // Load Animations
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);

        Fade fade = new Fade();
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.background, true);
        getWindow().setEnterTransition(fade);

        // Run Animasi
        form_login.startAnimation(bottom_to_top);

        // Onclick Button
        vBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                }
//                finishAffinity();

                vNip.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!vNip.getText().toString().isEmpty()) {
                            txtLayoutNip.setErrorEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                vPassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!vPassword.getText().toString().isEmpty()) {
                            txtLayoutPassword.setErrorEnabled(false);
                        }
                        else if (vPassword.getText().toString().length() > 7) {
                            txtLayoutPassword.setErrorEnabled(false);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

            }
        });

    }

    private boolean validate() {
        if (vNip.getText().toString().isEmpty()) {
            txtLayoutNip.setErrorEnabled(true);
            txtLayoutNip.setError("Nip tidak boleh kosong");
            return false;
        }
       else if (vPassword.getText().toString().isEmpty()) {
            txtLayoutPassword.setErrorEnabled(true);
            txtLayoutPassword.setError("Password tidak boleh kosong");
            return false;
        }
        else if (vPassword.getText().toString().length() < 8) {
            txtLayoutPassword.setErrorEnabled(true);
            txtLayoutPassword.setError("Password minimal 8 karakter");
            return false;

        } else  {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
