package com.ujjwalsingh.carezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    TextView name;
    private static int SPLASH_SCREEN = 3400;
    Animation top_anim,bottom_anim;
    LinearLayout linearLayout;
    LinearLayout layout_below;
   // LottieAnimationView lottieAnimationView;
    TextView descrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.name);
       //lottieAnimationView = findViewById(R.id.lot_name);
//
//        lottieAnimationView.s

        descrip = findViewById(R.id.description);
        linearLayout= findViewById(R.id.linear_layout);
        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        layout_below= findViewById(R.id.layout_below);

        linearLayout.setAnimation(top_anim);
        layout_below.setAnimation(bottom_anim);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, FirstActivity.class);
            startActivity(intent);
           // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }
    }, SPLASH_SCREEN);

    }
}