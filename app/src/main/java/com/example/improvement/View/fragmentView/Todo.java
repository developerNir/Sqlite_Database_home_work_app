package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.improvement.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class Todo extends Fragment {


    private DatabaseHelper databaseHelper;
    FloatingActionButton floatingActionButton;
    TextView textView;
    ListView listView;
    ConstraintLayout constraintLayout;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String>hashMap;
    Cursor cursor;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_todo, container, false);


        floatingActionButton = myView.findViewById(R.id.addFloatingActionButton);

        listView = myView.findViewById(R.id.listViewTodo);
        textView = myView.findViewById(R.id.massageTV);
        constraintLayout = myView.findViewById(R.id.constraintLayout);
        databaseHelper = new DatabaseHelper(getContext());

        cursor = databaseHelper.getAllData();
        // list view and data form Adapter ==========================
        if(cursor.getCount() == 0){
            textView.setVisibility(View.VISIBLE);
            textView.setText("No Data");
            Toast.makeText(getContext(), "No Data!", Toast.LENGTH_SHORT).show();
        }else {
            loadData(cursor);
        }


        // create Todo call ==============================


        addData();










        return myView;

    }

    public void addData(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                constraintLayout.setVisibility(View.GONE);


                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.todoFrameLayout, new createTodo());
                fragmentTransaction.commit();


            }
        });
    }

    // todo adapter list =====================================================

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
            View view1 = layoutInflater.inflate(R.layout.todo_item, null ,false);

            TextView title, description, createDate, statusTV;
            ImageButton deleteImageButton;

            title = view1.findViewById(R.id.titleTvTodo);
            description = view1.findViewById(R.id.desTvTodo);
            createDate = view1.findViewById(R.id.createDateTvTodo);
            statusTV = view1.findViewById(R.id.statusTvTodo);
            deleteImageButton = view1.findViewById(R.id.deleteButtonImage);

            hashMap = arrayList.get(i);
            String idTodo = hashMap.get("id");
            String titleTodo = hashMap.get("title");
            String DescriptionTodo = hashMap.get("description");
            String endDateTodo = hashMap.get("endDate");
            String createDateTodo = hashMap.get("createDate");
            String statusTodo = hashMap.get("status");


            title.setText(titleTodo);
            description.setText(DescriptionTodo);
            createDate.setText(createDateTodo);
            statusTV.setText(statusTodo);

            deleteImageButton.setOnClickListener(view2 -> {
                Integer var = databaseHelper.deleteTodoById(idTodo);


                if(var > 0){


                    Toast.makeText(getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Deletion Error", Toast.LENGTH_SHORT).show();
                }
            });



            return view1;
        }
    }

    public void loadData(Cursor cursor){

        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String endDate = cursor.getString(3);
                String createDate = cursor.getString(4);
                String status = cursor.getString(5);

                hashMap = new HashMap<>();
                hashMap.put("id", ""+id);
                hashMap.put("title", title);
                hashMap.put("description", description);
                hashMap.put("endDate", endDate);
                hashMap.put("createDate", createDate);
                hashMap.put("status", status);


                arrayList.add(hashMap);


            }

            listView.setAdapter(new MyAdapter());


        }
    }

}