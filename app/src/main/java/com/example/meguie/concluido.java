package com.example.meguie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class concluido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concluido);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        String idCliente = (String) intent.getStringExtra("id");
        String nomeCliente = (String) intent.getStringExtra("nome");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent1 = new Intent(concluido.this, homepage.class);
                intent1.putExtra("id", idCliente);
                intent1.putExtra("nome", nomeCliente);

                startActivity(intent1);
                finish();
            }
        },2900);

    }


}