package com.example.improvement.View.fragmentView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.improvement.R;

import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Note extends Fragment {

    FloatingActionButton floatingActionButton;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_note, container, false);

        floatingActionButton = myView.findViewById(R.id.addFloatingActionButton);




        floatingActionButton.setOnClickListener(view -> {

            databaseHelper = new DatabaseHelper(getContext());

            Boolean isCheck = databaseHelper.noteInsert("this is my fast note", "Programmimg is not a Craiar it is a fun and vary nice Working project ", "Sunday 03 December");
            if (isCheck){
                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
            }


        });



        return myView;
    }
}