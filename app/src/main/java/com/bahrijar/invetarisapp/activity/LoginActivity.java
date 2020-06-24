package com.bahrijar.invetarisapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Animation bottom_to_top;
    ConstraintLayout form_login;
    Button vBtnLogin;
    EditText vNip, vPassword;

    Handler handler;
    Runnable runnable;

    String iUsernip, iUserpassword;
    DatabaseReference mDatabase;

    String USERNIP_KEY = "usernipkey";
    String usernip_key = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabase = FirebaseDatabase.getInstance().getReference("User");

        form_login = findViewById(R.id.form_login);
        vBtnLogin = findViewById(R.id.btn_login);
        vNip = findViewById(R.id.edt_nip);
        vPassword = findViewById(R.id.edt_password);

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
                finishAffinity();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


//                iUsernip = vNip.getText().toString();
//                iUserpassword = vPassword.getText().toString();
//
//                if (iUsernip.equals("")) {
//                    vNip.setError("Masukkan NIP Anda");
//                    vNip.requestFocus();
//                } else if (iUserpassword.equals("")) {
//                    vPassword.setError("Masukkan Password Anda");
//                    vPassword.requestFocus();
//                } else {
//                    pushLogin(iUsernip, iUserpassword);
//                }

            }
        });

    }

//    private void pushLogin(final String iUsernip, final String iUserpassword) {
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child(iUsernip).exists()) {
//                    if (!iUsernip.isEmpty()) {
//                        User user = dataSnapshot.child(iUsernip).getValue(User.class);
//                        if (user.getUserpassword().equals(iUserpassword)) {
//                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
//                            finishAffinity();
//
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                        } else {
//                            Toast.makeText(LoginActivity.this, "Password Salah", Toast.LENGTH_LONG).show();
//                        }
//                    } else {
//                        Toast.makeText(LoginActivity.this, "User tidak ada", Toast.LENGTH_LONG).show();
//                    }
//                }
//                else {
//                    Toast.makeText(LoginActivity.this, "User tidak ada", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }


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
