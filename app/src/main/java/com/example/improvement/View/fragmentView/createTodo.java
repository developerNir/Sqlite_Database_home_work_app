package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;


public class createTodo extends Fragment {


    private DatabaseHelper databaseHelper;
    FloatingActionButton cancel, create;
    TextInputEditText titleEd, DesEd;
    ConstraintLayout constraintLayout;
    Boolean isInserted;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewInflater = inflater.inflate(R.layout.fragment_create_todo, container, false);


        cancel = viewInflater.findViewById(R.id.addFloatingActionButton);
        create = viewInflater.findViewById(R.id.createTodoButton);
        titleEd = viewInflater.findViewById(R.id.titleEd);
        DesEd = viewInflater.findViewById(R.id.desEd);

        constraintLayout = viewInflater.findViewById(R.id.constraintCreateTodo);

        databaseHelper = new DatabaseHelper(getContext());



        create.setOnClickListener(view -> {

            String titleValue = titleEd.getText().toString();
            String desValue = DesEd.getText().toString();

            if (titleValue.length()==0){
                titleEd.setError("Value is Emtiy");
            } else if (desValue.length() == 0) {
                DesEd.setError("Null Value");
            }else {
                isInserted  = databaseHelper.insertData(titleValue, desValue, "Satday 02 Dec", "Fryday 22 April", "Coming");

                if (isInserted){
                    Toast.makeText(getContext(), "Data Inserted...", Toast.LENGTH_SHORT).show();

                    constraintLayout.setVisibility(View.GONE);
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.createTodoFrameLayout, new Todo());
                    fragmentTransaction.commit();

                }else
                    Toast.makeText(getContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();

            }// end

        });// end Create =============================



        cancel.setOnClickListener(view -> {


            constraintLayout.setVisibility(View.GONE);



            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.createTodoFrameLayout, new Todo());
            fragmentTransaction.commit();

        });






        return viewInflater;
    }
}