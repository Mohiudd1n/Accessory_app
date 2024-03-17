package com.example.madmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {
    private ImageView splashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_splash_screen);

        splashImage = findViewById(R.id.splash);
        startSplashScreenWithAnimation();
    }

    private void startSplashScreenWithAnimation() {
        splashImage.setVisibility(View.VISIBLE);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(splashImage, "rotation", 0f, 360f);
        rotationAnimator.setDuration(600);

        // Scale animation using ObjectAnimator
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(splashImage, "scaleX", 0.4f, 1.3f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(splashImage, "scaleY", 0.4f, 1.3f);
        scaleXAnimator.setDuration(900);
        scaleYAnimator.setDuration(900);

        // Combine rotation and scale animations into an AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotationAnimator, scaleXAnimator, scaleYAnimator);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());


        animatorSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash_screen.this,MainActivity.class);

                startActivity(i);

                finish();
            }
        },2000);

    }
}