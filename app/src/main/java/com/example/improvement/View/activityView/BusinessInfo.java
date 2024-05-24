package com.example.improvement.View.activityView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.improvement.R;
import com.google.android.material.button.MaterialButton;

public class BusinessInfo extends AppCompatActivity {


    public static String COLUMN_ID;
    public static String COLUMN_TITEL;
    public static String COLUMN_DES;
    public static String COLUMN_HONE;
    public static String COLUMN_DONE;
    public static String COLUMN_HTWO;
    public static String COLUMN_DTWO;
    public static String COLUMN_HTHREE;
    public static String COLUMN_DTHREE;
    public static String COLUMN_HFOUR;
    public static String COLUMN_DFOUR;
    public static String COLUMN_HFIVE;
    public static String COLUMN_DFIVE;
    public static String COLUMN_LINK;
    public static String COLUMN_QAUTE;

    public static Bitmap bitmap;

    // Layout =====================================

    TextView tvTitle, tvDes, tvHone, tvDone, tvHtwo, tvDtwo, tvHthree, tvDthree, tvHfour, tvDfour, tvHfive, tvDfive, tvQoute;
    ImageView ivImage;
    MaterialButton likesButton, ShareButton, WhatsAppButton;
    TextView PreviousButtonTV, NextButtonTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_business_info);



            tvTitle = findViewById(R.id.titleTV);
            tvDes = findViewById(R.id.descriptionTV);
            tvHone = findViewById(R.id.headOneTV);
            tvDone = findViewById(R.id.desOneTV);
            tvHtwo = findViewById(R.id.headTwoTV);
            tvDtwo = findViewById(R.id.desTwoTV);
            tvHthree = findViewById(R.id.headThreeTV);
            tvDthree = findViewById(R.id.desThreeTV);
            tvHfour = findViewById(R.id.headFourTV);
            tvDfour = findViewById(R.id.desFourTV);
            tvHfive = findViewById(R.id.headFiveTV);
            tvDfive = findViewById(R.id.desFiveTV);

            tvQoute = findViewById(R.id.QauteTV);

            ivImage = findViewById(R.id.topImg);

            PreviousButtonTV = findViewById(R.id.previousTV);
            NextButtonTV = findViewById(R.id.NextTV);

            likesButton = findViewById(R.id.likesButton);
            ShareButton = findViewById(R.id.ShareButton);
            WhatsAppButton = findViewById(R.id.WhatsAppButton);



            tvTitle.setText(COLUMN_TITEL);
            tvDes.setText(COLUMN_DES);
            tvHone.setText(COLUMN_HONE);
            tvDone.setText(COLUMN_DONE);
            tvHtwo.setText(COLUMN_HTWO);
            tvDtwo.setText(COLUMN_DTWO);
            tvHthree.setText(COLUMN_HTHREE);
            tvDthree.setText(COLUMN_DTHREE);
            tvHfour.setText(COLUMN_HFOUR);
            tvDfour.setText(COLUMN_DFOUR);
            tvHfive.setText(COLUMN_HFIVE);
            tvDfive.setText(COLUMN_DFIVE);

            tvQoute.setText(COLUMN_QAUTE);

            ivImage.setImageBitmap(bitmap);

            PreviousButtonTV.setOnClickListener(view -> {
                Toast.makeText(BusinessInfo.this, "Previous", Toast.LENGTH_SHORT).show();
            });

            NextButtonTV.setOnClickListener(view -> {
                Toast.makeText(this, "Next", Toast.LENGTH_SHORT).show();
            });




            likesButton.setOnClickListener(view -> {
                Toast.makeText(this, "Like", Toast.LENGTH_SHORT).show();
                likesButton.setIcon(getResources().getDrawable(R.drawable.favorite_border_ic));
            });




        }
}