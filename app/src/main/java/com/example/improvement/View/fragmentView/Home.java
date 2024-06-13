package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Adapter.HomeAdapter;
import com.example.improvement.Service.Database.BlogDatabase.BlogDatabase;
import com.example.improvement.Service.Model.BusinessDataModel;

import java.util.ArrayList;
import java.util.HashMap;


public class Home extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter myAdapter;
    Context context = getContext();

    private BlogDatabase BlogDatabase;
    ArrayList<BusinessDataModel> arrayList = new ArrayList<>();
    BusinessDataModel businessDataModel;



    Cursor cursor;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = myView.findViewById(R.id.recyclerView);



        BlogDatabase myDatabase = new BlogDatabase(getContext());

        cursor = myDatabase.getAllData();
        // list view and data form Adapter ==========================
        if(cursor.getCount() == -1){
//            textView.setVisibility(View.VISIBLE);
//            textView.setText("No Data");
            Toast.makeText(getContext(), "No Data!", Toast.LENGTH_SHORT).show();
        }else {
            loadData(cursor);
        }








        return myView;
    }


    private void loadData(Cursor cursor){

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);

                String COLUMN_HONE = cursor.getString(3);
                String COLUMN_DONE = cursor.getString(4);
                String COLUMN_HTWO = cursor.getString(5);
                String COLUMN_DTWO = cursor.getString(6);
                String COLUMN_HTHREE = cursor.getString(7);
                String COLUMN_DTHREE = cursor.getString(8);
                String COLUMN_HFOUR = cursor.getString(9);
                String COLUMN_DFOUR = cursor.getString(10);
                String COLUMN_HFIVE = cursor.getString(11);
                String COLUMN_DFIVE = cursor.getString(12);
                String COLUMN_LINK = cursor.getString(13);
                String COLUMN_QAUTE = cursor.getString(14);
                byte[] image = cursor.getBlob(15);

                String myID = String.valueOf(id);

                businessDataModel = new BusinessDataModel(myID, title, description, COLUMN_HONE, COLUMN_DONE, COLUMN_HTWO, COLUMN_DTWO, COLUMN_HTHREE, COLUMN_DTHREE, COLUMN_HFOUR, COLUMN_DFOUR, COLUMN_HFIVE, COLUMN_DFIVE, COLUMN_LINK, COLUMN_QAUTE, image);


                arrayList.add(businessDataModel);


            }



            myAdapter = new HomeAdapter(arrayList, getContext());
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));





        }

    }// loadData method end =================


    // end ===========================================

}