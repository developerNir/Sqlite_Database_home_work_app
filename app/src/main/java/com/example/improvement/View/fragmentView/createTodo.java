package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;


public class createTodo extends Fragment {


    String DropItem;
    private DatabaseHelper databaseHelper;
    FloatingActionButton cancel, create;
    TextInputEditText titleEd, DesEd;
    ConstraintLayout constraintLayout;
    Boolean isInserted;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> arrayAdapter;
    String[] item ={"Upcoming", "Pandding", "Complete","Jest now" };



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


        // String-Array add from String.xml file ----------------------
        Context context=getContext();
        String[] language = context.getResources().getStringArray(R.array.status);


        autoCompleteTextView = viewInflater.findViewById(R.id.autoCompleteTextView);
        arrayAdapter = new ArrayAdapter<String>(context, R.layout.dropdown_layout, language);

        // text add array Adapter----------------------------------
        autoCompleteTextView.setAdapter(arrayAdapter);
        //onItem Selected item -------------------------------------
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DropItem = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(context, DropItem, Toast.LENGTH_SHORT).show();
            }
        });






        create.setOnClickListener(view -> {

            String titleValue = titleEd.getText().toString();
            String desValue = DesEd.getText().toString();

            if (titleValue.length()==0){
                titleEd.setError("Value is Emtiy");
            } else if (desValue.length() == 0) {
                DesEd.setError("Null Value");
            } else if (DropItem.length() == 0) {
                Toast.makeText(context, "Please Status Select", Toast.LENGTH_SHORT).show();
            } else {
                isInserted  = databaseHelper.insertData(titleValue, desValue, "Satday 02 Dec", "Fryday 22 April", DropItem);

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