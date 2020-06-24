package com.bahrijar.invetarisapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.bahrijar.invetarisapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    Animation splash;
    ImageView iv_splash;

    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Load Animation
        splash = AnimationUtils.loadAnimation(this, R.anim.splash);

        Fade fade = new Fade();
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.background, true);

        getWindow().setEnterTransition(fade);

        // Load Views
        iv_splash = findViewById(R.id.iv_splash);

        // Run Animasi
        iv_splash.startAnimation(splash);

        // Set Timer 2 detik
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent next = new Intent(SplashScreenActivity.this, LoginActivity.class);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreenActivity.this,
                        iv_splash, "iv_transition");
                startActivity(next, optionsCompat.toBundle());
            }
        }, 1000); // 2000 ms
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
