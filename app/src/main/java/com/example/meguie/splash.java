package com.example.meguie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {

    ImageView appname,splashimg;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        appname = findViewById(R.id.guidewname);
        splashimg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        splashimg.animate().translationY(-4500).setDuration(1500).setStartDelay(2000);
        appname.animate().translationY(4000).setDuration(1500).setStartDelay(2000);
        lottieAnimationView.animate().translationY(2500).setDuration(1500).setStartDelay(2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(splash.this,MainActivity.class));
                finish();
            }
        },2900);
    }
}