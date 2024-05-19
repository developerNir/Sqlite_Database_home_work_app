package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Note_Update extends Fragment {

     LinearLayout frameLayout;
     public static String titleText, des, id;
     TextInputEditText titleEd,edit_text;
     Button buttonOk,buttonCancle;
     ImageView backButton;
     String formattedDate;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_note__update, container, false);

        frameLayout = myView.findViewById(R.id.updateNoteLayout);


        backButton = myView.findViewById(R.id.backButton);
        titleEd = myView.findViewById(R.id.dialog_title);
        edit_text = myView.findViewById(R.id.edit_text);
        buttonOk = myView.findViewById(R.id.btn_dialog_Ok);
        buttonCancle = myView.findViewById(R.id.btn_dialog_cancle);

        titleEd.setText(titleText);
        edit_text.setText(des);


        backButton.setOnClickListener(view -> {

            frameLayout.setVisibility(View.GONE);

            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.updateNoteFrameLayout, new Note());
            fragmentTransaction.commit();
        });

        buttonOk.setOnClickListener(view -> {
            String title = titleEd.getText().toString();
            String des = edit_text.getText().toString();


            if (title.length() == 0){
                titleEd.setError("Value is Empty");
            } else if (des.length() == 0) {
                edit_text.setError("Value is Empty");
            }else {

                LocalDateTime myDateObj = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    myDateObj = LocalDateTime.now();
                    System.out.println("Before formatting: " + myDateObj);
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    formattedDate = myDateObj.format(myFormatObj);
                }



                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());



                Boolean isCheck = databaseHelper.updateNoteData(id,title,des,formattedDate);

                if (isCheck){

                    frameLayout.setVisibility(View.GONE);

                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.updateNoteFrameLayout, new Note());
                    fragmentTransaction.commit();
                    Toast.makeText(getContext(), "Data is Update", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Date is not Update", Toast.LENGTH_SHORT).show();

                }




            }


        });

        buttonCancle.setOnClickListener(view -> {

            frameLayout.setVisibility(View.GONE);

            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.updateNoteFrameLayout, new Note());
            fragmentTransaction.commit();


        });









        return myView;
    }
}