package com.example.improvement.View.fragmentView.Dream;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.example.improvement.Service.Model.DreamModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class DreamTab extends Fragment {

    MaterialButton addItem,addDreamBtn, addEndDateBtn;
    TextInputEditText dreamDesEd, dreamTitleEd;
    TextInputLayout dreamTitleLayout, dreamDesLayout;
    LinearLayout addDreamLayout;
    ListView recyclerView;
    ImageView addImageView;
    Uri ImagePath;
    DatePickerDialog picker;

    DatabaseHelper databaseHelper;
    byte[] imageBit;
    String createDate, myEndDate;
    Bitmap bitmap;
    ArrayList<DreamModel> arrayList = new ArrayList<>();
    private DreamModel dreamModel;

    TextView textMassage;


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

        textMassage = myView.findViewById(R.id.createMassage);

        dreamTitleEd = myView.findViewById(R.id.dreamTitleEd);
        dreamDesEd = myView.findViewById(R.id.dreamDesEd);
        dreamDesLayout = myView.findViewById(R.id.DescriptionEdLayout);
        dreamTitleLayout = myView.findViewById(R.id.titleEdLayout);



        addItem = myView.findViewById(R.id.addItem);



        databaseHelper = new DatabaseHelper(getContext());





        // add Data into Database in Button Click =====================


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    addDreamBtn.setVisibility(View.VISIBLE);
                    textMassage.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    if (ImagePath != null){
                    try {
                        imageBit = getBitmapAsByteArray(getBitmapFromUri(ImagePath));
                    } catch (IOException e) {
                        Toast.makeText(getContext(), "Select Image", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    }else {
                        Toast.makeText(getContext(), "Select Image", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String title = dreamTitleEd.getText().toString();
                    String des = dreamDesEd.getText().toString();





                    boolean addIs = true;

                    // Check for empty fields first
                    if (title.isEmpty()) {
                        dreamTitleLayout.setError("Value is Empty");
                        return;
                    } else if (des.isEmpty()) {
                        dreamDesLayout.setError("Value is Empty");
                        return;
                    }



                    databaseHelper = new DatabaseHelper(getContext());

                    LocalDateTime myDateObj = null;
                    String formattedDate = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        myDateObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE dd-MMMM-yyyy HH:mm:ss");
                        formattedDate = myDateObj.format(myFormatObj);
                    }

                    boolean isCheck;
                    if (addIs) {
                        isCheck = databaseHelper.DreamDataInsert(title, des, imageBit, formattedDate, "90/30/2024" );
                    } else {
                        isCheck = databaseHelper.DreamDataInsert(title,des, imageBit, formattedDate, "90/30/2024");
                    }

                    loadData();

                    if (isCheck) {
                        addDreamLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        dreamTitleEd.setText("");
                        dreamDesEd.setText("");
                        Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Error inserting data", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    // Log the exception
                    Log.e("DialogButtonOkClick", "Error in onClick", e);
                    Toast.makeText(getContext(), "Please Select Image and Other Fields", Toast.LENGTH_SHORT).show();
                }

            }

        });
        // data add button end =========================


        addImageView.setOnClickListener(view -> {

            requestPermissions();


        });

        addDreamBtn.setOnClickListener(view -> {

            textMassage.setVisibility(View.GONE);
            addDreamBtn.setVisibility(View.GONE);
            addDreamLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);


        });



        // Load data form Recyceler View ========


        loadData();



        return myView;
    }



    // Adapter and BaseAdapter ==================

    public class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.blog_card_item, null ,false);

            TextView titleTv, DesTv, textView3;
            ImageView imageView,deleteImage;

            deleteImage = view1.findViewById(R.id.deleteImage);
            titleTv = view1.findViewById(R.id.titleTv);
            imageView = view1.findViewById(R.id.imageView);
            DesTv = view1.findViewById(R.id.DesTv);
            textView3 = view1.findViewById(R.id.textView3);





            DreamModel item = arrayList.get(i);
            titleTv.setText(item.getTitle());
            byte[] newImage = item.getImage();
            int id = item.getId();
            String myId = String.valueOf(id);

            DesTv.setText(item.getDes());
            textView3.setText(item.getCreateDate());



            deleteImage.setOnClickListener(viewDelete ->{

                int isDelete = databaseHelper.DeleteDreamById(myId);


                if(isDelete > 0){
                    loadData();
                    Toast.makeText(getContext(), "Data Deleted and Click the Dream Tab to Refresh", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Deletion Error", Toast.LENGTH_SHORT).show();
                }

            });

            Bitmap bmp = BitmapFactory.decodeByteArray(newImage, 0, newImage.length);






//            byte[] data = item1.getBytes();
            imageView.setImageBitmap(bmp);





            return view1;
        }
    }

    public void loadData(){


        Cursor cursor = databaseHelper.getAllDreamData();
        arrayList.clear();

        if (cursor.getCount()== 0){
            textMassage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            textMassage.setText("Click the Button below to add your Dream");
            Toast.makeText(getContext(), "No Data!", Toast.LENGTH_SHORT).show();
        }



        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){

                int columnIndex = cursor.getColumnIndex("id");
                int columnIndex1 = cursor.getColumnIndex("title");
                int columnIndex2 = cursor.getColumnIndex("description");
                int columnIndex3 = cursor.getColumnIndex("image");
                int columnIndex4 = cursor.getColumnIndex("createDate");
                int columnIndex5 = cursor.getColumnIndex("endDate");



                int id = cursor.getInt(columnIndex);
                String title = cursor.getString(columnIndex1);
                String description = cursor.getString(columnIndex2);
                byte[] blob = cursor.getBlob(columnIndex3);


                String createDate = cursor.getString(columnIndex4);
                String endDate = cursor.getString(columnIndex5);





                if (blob != null) {
                    dreamModel = new DreamModel(id,title,description,blob,createDate,endDate);
                    // Process the blob data
                } else {
                    Log.e("DreamTab", "BLOB data is null");
                }

                if (arrayList == null) {
                    arrayList = new ArrayList<>(); // Initialize if null to prevent NullPointerException
                }
                arrayList.add(dreamModel);

            }

            MyAdapter myAdapter = new MyAdapter();
            recyclerView.setAdapter(myAdapter);



        }
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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
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



}