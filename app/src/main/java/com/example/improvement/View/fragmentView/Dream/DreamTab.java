package com.example.improvement.View.fragmentView.Dream;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.improvement.R;
import com.example.improvement.Service.Adapter.MyAdapter;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class DreamTab extends Fragment {

    MaterialButton addItem,addDreamBtn, addEndDateBtn;
    TextInputEditText dreamDesEd, dreamTitleEd;
    TextInputLayout dreamTitleLayout, dreamDesLayout;
    LinearLayout addDreamLayout;
    RecyclerView recyclerView;
    ImageView addImageView;
    Uri ImagePath;
    DatePickerDialog picker;

    DatabaseHelper databaseHelper;
    byte[] imageBit;
    String createDate, EndDate, myEndDate;
    Bitmap bitmap;


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

        dreamTitleEd = myView.findViewById(R.id.dreamTitleEd);
        dreamDesEd = myView.findViewById(R.id.dreamDesEd);
        dreamDesLayout = myView.findViewById(R.id.DescriptionEdLayout);
        dreamTitleLayout = myView.findViewById(R.id.titleEdLayout);

        addEndDateBtn = myView.findViewById(R.id.addEndDate);

        addItem = myView.findViewById(R.id.addItem);



        databaseHelper = new DatabaseHelper(getContext());


        addEndDateBtn.setOnClickListener(view -> {

            myEndDate = datePickerSetAndEnd(getContext());

            addEndDateBtn.setText(myEndDate);


        });


        // add Data into Database in Button Click =====================
        addItem.setOnClickListener(view -> {


            try {
                imageBit = getBitmapAsByteArray(getBitmapFromUri(ImagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            myEndDate = addEndDateBtn.getText().toString();

            addDreamLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            String title = dreamTitleEd.getText().toString();
            String des = dreamDesEd.getText().toString();

            if (myEndDate == null){
                Toast.makeText(getContext(), "Select your End Date", Toast.LENGTH_SHORT).show();

            } else if (imageBit == null){
                Toast.makeText(getContext(), "Select Image", Toast.LENGTH_SHORT).show();
            }
            else if (title.isEmpty()){
                dreamTitleLayout.setError("Enter Title");
            } else if (des.isEmpty()){
                dreamDesLayout.setError("Enter Description");
            }else {

                LocalDateTime myDateObj = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE dd-MMMM-yyyy HH:mm:ss");
                    createDate = myDateObj.format(myFormatObj);
                }

                databaseHelper = new DatabaseHelper(getContext());
                boolean isInserted = databaseHelper.DreamDataInsert(title,des, imageBit, createDate, "90/30/2024");

                if (isInserted){
                    Toast.makeText(getContext(), "Dream Added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }

                dreamTitleEd.setText("");
                dreamDesEd.setText("");


            }


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

        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


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

//                        try {
//                            bitmap = getBitmapFromUri(ImagePath);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }

                        Picasso.get().load(ImagePath).into(addImageView);
                    }
                }
            });


    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ContentResolver contentResolver = getContext().getContentResolver();
        InputStream inputStream = contentResolver.openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
        return bitmap;
    }




    private String datePickerSetAndEnd(Context context){

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        Log.d("log", "datePickerSetAndEnd: "+day+month+year);

        addEndDateBtn.setText(day+"-"+month+"-"+year);


        // date picker dialog
        picker = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {



                        if(monthOfYear == 0){
                            EndDate = "January "+dayOfMonth;

                            Toast.makeText(getContext(), "January", Toast.LENGTH_SHORT).show();
                        }else if (monthOfYear == 1){
                            EndDate = "February "+dayOfMonth;

                            Toast.makeText(getContext(), "February", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 2){
                            EndDate = "Merch "+dayOfMonth;

                            Toast.makeText(getContext(), "Merch", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 3){
                            EndDate = "April "+dayOfMonth;

                            Toast.makeText(getContext(), "April", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 4){
                            EndDate = "May "+dayOfMonth;

                            Toast.makeText(getContext(), "May", Toast.LENGTH_SHORT).show();
                        }else if (monthOfYear == 5){
                            EndDate = "June "+dayOfMonth;
                            Toast.makeText(getContext(), "June", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 6){
                            EndDate = "July "+dayOfMonth;
                            Toast.makeText(getContext(), "July", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 7){
                            EndDate = "August "+dayOfMonth;

                            Toast.makeText(getContext(), "August", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 8){
                            EndDate = "September "+dayOfMonth;

                            Toast.makeText(getContext(), "September", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 9){
                            EndDate = "October "+dayOfMonth;

                            Toast.makeText(getContext(), "October", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 10){
                            EndDate = "November "+dayOfMonth;

                            Toast.makeText(getContext(), "November", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 11){
                            EndDate = "December "+dayOfMonth;

                            Toast.makeText(getContext(), "December", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, year, month, day);
        picker.show();
        return EndDate;
    }


}