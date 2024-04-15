package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class ExpenseList extends Fragment {

    ListView listView;
    Cursor cursor;
    ArrayList<HashMap<String, String>>arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_expense_list, container, false);

        listView = myView.findViewById(R.id.expenseList);
        databaseHelper = new DatabaseHelper(getContext());


        Cursor cursor = databaseHelper.getAllDataExpense();
        loadExpenseData(cursor);




        return  myView;

    }

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
            View view1 = layoutInflater.inflate(R.layout.expense_list_view, null ,false);

            TextView titleTv, whereTv, priceTv, timeTv;
            ImageView deleteImageButton;

            titleTv = view1.findViewById(R.id.titleTv);
            whereTv = view1.findViewById(R.id.whereTv);
            priceTv = view1.findViewById(R.id.productTv);
            timeTv = view1.findViewById(R.id.TimeTv);
            deleteImageButton = view1.findViewById(R.id.deleteWallet);


            // expense list =================================
            hashMap = arrayList.get(i);
            String idExpense = hashMap.get("idEx");
            String titleExpense = hashMap.get("titleEx");
            String whereExpense = hashMap.get("whareEx");
            String timeExpenseValue = hashMap.get("timeEx");
            String amount = hashMap.get("productEx");






                timeTv.setText(timeExpenseValue);
                whereTv.setText(whereExpense);
                priceTv.setText("$ "+amount);
                titleTv.setText(titleExpense);


            deleteImageButton.setOnClickListener(view2 -> {
                int var  = databaseHelper.deleteExpenseById(idExpense);


                if(var > 0){


                    Toast.makeText(getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Deletion Error", Toast.LENGTH_SHORT).show();
                }
            });



            return view1;
        }
    }

    // ========== load data ====================================

    public void loadExpenseData(Cursor cursor) {

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String endDate = cursor.getString(3);
                String productPrice = cursor.getString(4);

                hashMap = new HashMap<>();
                hashMap.put("idEx", "" + id);
                hashMap.put("titleEx", title);
                hashMap.put("whareEx", description);
                hashMap.put("timeEx", endDate);
                hashMap.put("productEx", productPrice);


                arrayList.add(hashMap);


            }

            listView.setAdapter(new MyAdapter());


        }
    }


}