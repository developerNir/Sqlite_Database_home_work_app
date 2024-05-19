package com.example.improvement.View.activityView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.improvement.R;

public class SplashScreen extends AppCompatActivity {

    TextView textView;

    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        textView = findViewById(R.id.textview);



        // how to app system ber hide ==========================================
        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        // Configure the behavior of the hidden system bars.
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );

        // Hide both the status bar and the navigation bar.
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());


        // zoom animation add our onCreate function -----------------------------
        startZoomAnimation();






        // delayed splash screen time and navigation ===============================
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                // sharePreference --------------------------------------
                sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

                Boolean firstTimeGet = sharedPreferences.getBoolean("firstTime", true);


                if (firstTimeGet){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstTime", false);
                    editor.apply();

                    Intent mainIntent = new Intent(SplashScreen.this, OnBoarding.class);
                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();

                }else {
                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();
                }

            }
        },3000);







    }


    // text zoom animation ================================
    private void startZoomAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 1.5f, // Start and end scale X
                1f, 1.5f, // Start and end scale Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X
                Animation.RELATIVE_TO_SELF, 0.5f // Pivot point Y
        );
        scaleAnimation.setDuration(2000); // Animation duration in milliseconds
        scaleAnimation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        scaleAnimation.setRepeatMode(Animation.REVERSE); // Reverse the animation at the end
        textView.startAnimation(scaleAnimation);
    }

}