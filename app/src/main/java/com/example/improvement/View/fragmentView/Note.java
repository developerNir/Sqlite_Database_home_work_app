package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.improvement.R;

import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class Note extends Fragment {

    FloatingActionButton floatingActionButton;
    private DatabaseHelper databaseHelper;
    TextView textView;
    ListView listView;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String>hashMap;
    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_note, container, false);

        floatingActionButton = myView.findViewById(R.id.addFloatingActionButton);

        textView = myView.findViewById(R.id.massageTV);
        listView = myView.findViewById(R.id.listViewNote);

        databaseHelper = new DatabaseHelper(getContext());

        Cursor cursor = databaseHelper.getAllDataFromNote();
//        StringBuffer buffer = new StringBuffer();
        if(cursor.getCount() == 0){
            textView.setVisibility(View.VISIBLE);
            textView.setText("No Note");
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else {
            loadData(cursor);
        }
//        while (cursor.moveToNext()){
//            buffer.append("id : " + cursor.getString(0) + "\n");
//            buffer.append("title : " + cursor.getString(1) + "\n");
//            buffer.append("description : " + cursor.getString(2) + "\n");
//            buffer.append("startTime : " + cursor.getString(3) + "\n\n");
//        }
//



        floatingActionButton.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


            builder.setTitle("Enter Data");
            View viewDialog = getLayoutInflater().inflate(R.layout.create_todo_layout, null);


            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.show();


//            databaseHelper = new DatabaseHelper(getContext());
//
//            Boolean isCheck = databaseHelper.noteInsert("this is my fast note", "Programmimg is not a Craiar it is a fun and vary nice Working project ", "Sunday 03 December");
//            if (isCheck){
//                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
//            }


        });



        return myView;
    }

    // List and Adapter ==========================note ========================
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
            View view1 = layoutInflater.inflate(R.layout.note_item_list, null ,false);

            TextView title, description, startTime;
            title = view1.findViewById(R.id.titleNoteTv);
            description = view1.findViewById(R.id.descriptionNoteTv);
            startTime = view1.findViewById(R.id.startTimeTv);

            hashMap = arrayList.get(i);
            String titleNote = hashMap.get("title");
            String DescriptionNote = hashMap.get("description");
            String startTimeNote = hashMap.get("startTime");


            title.setText(titleNote);
            description.setText(DescriptionNote);
            startTime.setText(startTimeNote);


            return view1;
        }
    }

    public void loadData(Cursor cursor){

        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String startTime = cursor.getString(3);

                hashMap = new HashMap<>();
                hashMap.put("id", ""+id);
                hashMap.put("title", title);
                hashMap.put("description", description);
                hashMap.put("startTime", startTime);


                arrayList.add(hashMap);


            }

            listView.setAdapter(new MyAdapter());


        }
    }

}