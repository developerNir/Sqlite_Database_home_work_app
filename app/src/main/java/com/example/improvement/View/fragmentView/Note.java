package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.improvement.R;

import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

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
    ConstraintLayout frameLayout_Note;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_note, container, false);

        floatingActionButton = myView.findViewById(R.id.addFloatingActionButton);

        textView = myView.findViewById(R.id.massageTV);
        listView = myView.findViewById(R.id.listViewNote);
        frameLayout_Note = myView.findViewById(R.id.noteConstrait);

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



        // AlertDialog =================note create =========================
        floatingActionButton.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


            builder.setTitle("Note Create");
            View viewDialog = getLayoutInflater().inflate(R.layout.create_todo_layout, null);


            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextInputEditText titleEd,desEd,statusEd;
            titleEd = (TextInputEditText) viewDialog.findViewById(R.id.dialog_title);
            desEd = (TextInputEditText) viewDialog.findViewById(R.id.edit_text);
            statusEd = (TextInputEditText) viewDialog.findViewById(R.id.statusTextEd);

            Button dialogButtonCancle = (Button) viewDialog.findViewById(R.id.btn_dialog_cancle);
            dialogButtonCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            // Ok button ==================================
            Button dialogButtonOk = (Button) viewDialog.findViewById(R.id.btn_dialog);
            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }

                @Override
                public void onClick(View v) {

                    String title = titleEd.getText().toString();
                    String des = desEd.getText().toString();
                    String status = statusEd.getText().toString();

                    if (title.length()==0){
                        titleEd.setError("Value is Emtiy");
                    } else if (des.length() == 0) {
                        desEd.setError("Value is Emtiy");

                    } else if (status.length() == 0) {
                        statusEd.setError("Value is Emtiy");

                    }else {

                        databaseHelper = new DatabaseHelper(getContext());

                        Boolean isCheck = databaseHelper.noteInsert(title, des, status);
                        if (isCheck){
                            alertDialog.dismiss();

                            //  code is Reload Fragment ==========

                            Toast.makeText(getContext(), "Note Data inserted ...", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });

            alertDialog.show();



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
            LinearLayout linearLayout;

            title = view1.findViewById(R.id.titleNoteTv);
            description = view1.findViewById(R.id.descriptionNoteTv);
            startTime = view1.findViewById(R.id.startTimeTv);
            linearLayout = view1.findViewById(R.id.note_Item_Linear);

            hashMap = arrayList.get(i);
            String idNote = hashMap.get("id");
            String titleNote = hashMap.get("title");
            String DescriptionNote = hashMap.get("description");
            String startTimeNote = hashMap.get("startTime");

            linearLayout.setOnClickListener(view2 -> {

                frameLayout_Note.setVisibility(View.GONE);

                Note_Update.titleText = titleNote;
                Note_Update.des = DescriptionNote;
                Note_Update.status = startTimeNote;
                Note_Update.id = idNote;

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.note_FrameLayout, new Note_Update());
                fragmentTransaction.commit();




            });

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