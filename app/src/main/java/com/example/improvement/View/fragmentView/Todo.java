package com.example.improvement.View.fragmentView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.example.improvement.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Todo extends Fragment {


    private TodoViewModel todoViewModel;
    FloatingActionButton floatingActionButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_todo, container, false);


        floatingActionButton = myView.findViewById(R.id.addFloatingActionButton);



        // create Todo call ==============================
        floatingActionButton.setOnClickListener(view -> {

            addData();

        });








        return myView;

    }

    public void addData(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

                boolean isInserted  = databaseHelper.insertData("Android studio update", "I have success for the development sqlite database", "Satday 02 Dec", "Fryday 22 April", "Coming");

                if (isInserted){
                    Toast.makeText(getContext(), "Data Inserted...", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }




}