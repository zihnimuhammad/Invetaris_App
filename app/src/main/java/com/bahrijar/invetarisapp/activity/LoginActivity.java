package com.bahrijar.invetarisapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.activity.mahasiswa.MainActivity;
import com.bahrijar.invetarisapp.activity.petugas.MainPetugasActivity;
import com.bahrijar.invetarisapp.model.User;
import com.bahrijar.invetarisapp.network.ServiceGenerator;
import com.bahrijar.invetarisapp.network.response.UserResponse;
import com.bahrijar.invetarisapp.network.service.ApiInterface;
import com.bahrijar.invetarisapp.utils.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Animation bottom_to_top, login;
    ConstraintLayout form_login;
    Button vBtnLogin;
    private TextInputEditText vNip, vPassword;
    ImageView iv_login;

    private TextInputLayout txtLayoutNip, txtLayoutPassword;
    ApiInterface apiInterface;

    private ProgressDialog dialog;

    SharedPrefManager sharedPrefManager;

    Handler handler;
    Runnable runnable;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()) {
            if (sharedPrefManager.getSPRole().equals("mahasiswa")) {
                startActivity(new Intent(LoginActivity.this, com.bahrijar.invetarisapp.activity.mahasiswa.MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            } else if (sharedPrefManager.getSPRole().equals("petugas")) {
                startActivity(new Intent(LoginActivity.this, com.bahrijar.invetarisapp.activity.MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }

        }


    }


    private void init() {
        // Load Views
        mContext = this;
        form_login = findViewById(R.id.form_login);
        vBtnLogin = findViewById(R.id.btn_login);
        vNip = findViewById(R.id.edt_nip);
        vPassword = findViewById(R.id.edt_password);
        txtLayoutNip = findViewById(R.id.txt_layout_nip);
        txtLayoutPassword = findViewById(R.id.txt_layout_password);

        iv_login = findViewById(R.id.iv_login);

        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setCancelable(false);


        // Load Animations
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        login = AnimationUtils.loadAnimation(this, R.anim.login);

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
                    login();
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
                        } else if (vPassword.getText().toString().length() > 7) {
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
            txtLayoutNip.setError("Nomor Induk tidak boleh kosong");
            return false;
        } else if (vPassword.getText().toString().isEmpty()) {
            txtLayoutPassword.setErrorEnabled(true);
            txtLayoutPassword.setError("Password tidak boleh kosong");
            return false;
        } else if (vPassword.getText().toString().length() < 8) {
            txtLayoutPassword.setErrorEnabled(true);
            txtLayoutPassword.setError("Password minimal 8 karakter");
            return false;

        }
        return true;
    }

    private void login() {
        dialog.setMessage("Logging in");
        dialog.show();

        apiInterface = ServiceGenerator.createBaseService(this, ApiInterface.class);
        apiInterface.loginRequest(vNip.getText().toString(), vPassword.getText().toString()).enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                try {

                    if (response.code() == 200) {
                        User user = response.body().getUser();
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, user.getName());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ROLE, user.getRole());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, "Bearer " + response.body().getToken());
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                        if (user.getRole().equals("mahasiswa")) {
                            startActivity(new Intent(mContext, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();

                        } else if (user.getRole().equals("petugas")) {
                            startActivity(new Intent(mContext, com.bahrijar.invetarisapp.activity.MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }

                    } else {
                        Toast.makeText(mContext, "No Induk/Password salah", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
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
