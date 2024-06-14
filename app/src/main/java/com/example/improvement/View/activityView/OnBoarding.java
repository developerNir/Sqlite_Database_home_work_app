package com.example.improvement.View.activityView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.improvement.R;
import com.example.improvement.Service.Adapter.SliderAdapter;

public class OnBoarding extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dotsLayout;
    TextView[] dotsText;

    Button buttonGetStart, skipButton, nextBtn;

    SliderAdapter sliderAdapter;
    int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        // element Introducing ====================================
        viewPager = findViewById(R.id.viewPager2);
        dotsLayout = findViewById(R.id.linear_layout);
        buttonGetStart = findViewById(R.id.nextButton);
        skipButton = findViewById(R.id.skipButton);
        nextBtn = findViewById(R.id.nextBtn);


        // slider adapter object create and view page add adapter ----------------------
        sliderAdapter = new SliderAdapter(OnBoarding.this);
        viewPager.setAdapter(sliderAdapter);

        // create dots  call---------------------------------
        createDots(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        // skip Button -------------------------------------
        skipButton.setOnClickListener(view -> {
            startActivity(new Intent(OnBoarding.this, MainActivity.class));
            finish();
        });

        // get started button -------------------------------------
        buttonGetStart.setOnClickListener(view -> {
            startActivity(new Intent(OnBoarding.this, MainActivity.class));
            finish();
        });

        // View Page increment ==========================
        nextBtn.setOnClickListener(view -> {
            viewPager.setCurrentItem(currentPosition+1);
        });



    }// end OnCreate -------------------------------------

    // create Dots -------------------------------------
    private void createDots(int position){

        dotsText = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i=0 ; i<dotsText.length; i++){

            dotsText[i] = new TextView(this);
            dotsText[i].setText(Html.fromHtml("&#8226;"));
            dotsText[i].setTextSize(35);

            dotsLayout.addView(dotsText[i]);
        }
        if (dotsText.length > 0){
            dotsText[position].setTextColor(getColor(R.color.primaryColor));
        }


    }

    // View page OnPageChangeListener -------------------------------------------
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {

            createDots(position);
            currentPosition = position;

            // Let's get started button visibility add -----------------------
            if (position == 0){
                buttonGetStart.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                buttonGetStart.setVisibility(View.INVISIBLE);
            } else if (position == 2) {
                buttonGetStart.setVisibility(View.INVISIBLE);
            } else if (position == 3) {
                buttonGetStart.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}