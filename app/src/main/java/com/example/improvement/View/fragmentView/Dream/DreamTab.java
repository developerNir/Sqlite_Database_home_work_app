package com.example.improvement.View.fragmentView.Dream;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.improvement.R;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;


public class DreamTab extends Fragment {

    MaterialButton addItem,addDreamBtn;
    LinearLayout addDreamLayout;
    RecyclerView recyclerView;
    ImageView addImageView;
    Uri ImagePath;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_dream_tab, container, false);


        addDreamBtn = myView.findViewById(R.id.addDreamBtn);
        addDreamLayout = myView.findViewById(R.id.addDreamLayout);
        recyclerView = myView.findViewById(R.id.recyclerView);

        addImageView = myView.findViewById(R.id.addImageView);
        addItem = myView.findViewById(R.id.addItem);


        addItem.setOnClickListener(view -> {

            addDreamLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        });


        addImageView.setOnClickListener(view -> {

            requestPermissions();


        });

        addDreamBtn.setOnClickListener(view -> {

            addDreamLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);


//            if (addDreamBtn.getVisibility() == View.VISIBLE){
//                addDreamLayout.setVisibility(View.GONE);
//                recyclerView.setVisibility(View.VISIBLE);
//
//                addDreamBtn.setIcon(getResources().getDrawable(R.drawable.baseline_check_24));
//
//            }
//
//            if (addDreamBtn.getVisibility() == View.GONE)
//            {
//                addDreamLayout.setVisibility(View.VISIBLE);
//                recyclerView.setVisibility(View.GONE);
//
//                addDreamBtn.setIcon(getResources().getDrawable(R.drawable.add_ic));
//
//            }
        });



        return myView;
    }



    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted
            SelectImage();
        } else {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
    }



    // select image insight form the gallery

    private void SelectImage() {
        Intent newIntent = new Intent();
        newIntent.setType("image/*");
        newIntent.setAction(Intent.ACTION_GET_CONTENT);
//       startActivityForResult(newIntent, 1);
        startActivityIntent.launch(newIntent);

    }
    // onActivityResult Override Method ----------------------------

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Add same code that you want to add in onActivityResult method
                    if (result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        ImagePath = data.getData();
                        Picasso.get().load(ImagePath).into(addImageView);
                    }
                }
            });

}