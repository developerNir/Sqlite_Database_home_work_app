package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;


public class Note_Update extends Fragment {

     LinearLayout frameLayout;
     public static String titleText, des, status, id;
     TextInputEditText titleEd,statusTextEd,edit_text;
     Button buttonOk,buttonCancle;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_note__update, container, false);

        frameLayout = myView.findViewById(R.id.updateNoteLayout);


        titleEd = myView.findViewById(R.id.dialog_title);
        statusTextEd = myView.findViewById(R.id.statusTextEd);
        edit_text = myView.findViewById(R.id.edit_text);
        buttonOk = myView.findViewById(R.id.btn_dialog_Ok);
        buttonCancle = myView.findViewById(R.id.btn_dialog_cancle);

        titleEd.setText(titleText);
        statusTextEd.setText(status);
        edit_text.setText(des);


        buttonOk.setOnClickListener(view -> {
            String title = titleEd.getText().toString();
            String des = edit_text.getText().toString();
            String Status = statusTextEd.getText().toString();

            if (title.length() == 0){
                titleEd.setError("Value is Empty");
            } else if (des.length() == 0) {
                edit_text.setError("Value is Empty");
            } else if (Status.length()==0) {
                statusTextEd.setError("Value is Empty");
            }else {

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

                Boolean isCheck = databaseHelper.updateNoteData(id,title,des,Status);

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