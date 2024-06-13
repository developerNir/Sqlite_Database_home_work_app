package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Wallet extends Fragment {

    // variable for our bar chart
//    private BarChart barChart;
//
//    // variable for our bar data set.
//    private BarDataSet barDataSet1, barDataSet2;

    // array list for storing entries.


    // creating a string array for displaying days.
    //String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};

    String totalIncomeVar = "0", totalExpenseVar = "0";

    // Extended Flowting Action Button ===================
    ExtendedFloatingActionButton addManyIncome ,incomButton, expressButton;
    TextView incomButtonTopTexView, expressButtonTextView;
    Boolean isActionsView;
    TextView mainCashTv, TotalExpenseTV, totalIncomeTV;


    // Alert dialog =========
    AlertDialog alertDialog;

    // Tab Button ==============
    Button ButtonTabIncome,ButtonTabExpress;
    ListView listViewWallet, ExpenseList;
    LinearLayout expenseViewListLinearLayout, incomeLinearLayout;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    ArrayList<HashMap<String, String>> arrayList2 = new ArrayList<>();
    HashMap<String,String> hashMap;
    HashMap<String,String> hashMap2;
    //FrameLayout frameLayout;


    //String formattedDate;


    private DatabaseHelper databaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_wallet, container, false);


//        frameLayout = myView.findViewById(R.id.myFrameLayout);
        addManyIncome = myView.findViewById(R.id.addManyIncome);
        expressButton = myView.findViewById(R.id.expressButton);
        incomButton = myView.findViewById(R.id.incomButton);
        // floating Action Button ======================
        incomButtonTopTexView = myView.findViewById(R.id.incomButtonTopTexView);
        expressButtonTextView = myView.findViewById(R.id.expressButtonTextView);

        // Total many ====================
        mainCashTv = myView.findViewById(R.id.mainCashTv);
        totalIncomeTV = myView.findViewById(R.id.totalIncomeTV);
        TotalExpenseTV = myView.findViewById(R.id.TotalExpenseTV);

        // List View and Button ========================
        ButtonTabIncome = myView.findViewById(R.id.buttonTabIncome);
        ButtonTabExpress = myView.findViewById(R.id.buttonTabExpress);




        // List View ==================================


        listViewWallet = myView.findViewById(R.id.walletList);
        ExpenseList = myView.findViewById(R.id.expenseWalletList);

        // List View Gone and Visible ================================
        incomeLinearLayout = myView.findViewById(R.id.incomeLinearLayout);
        expenseViewListLinearLayout = myView.findViewById(R.id.expenseLinearLayout);





        incomButton.setVisibility(View.GONE);
        expressButton.setVisibility(View.GONE);
        incomButtonTopTexView.setVisibility(View.GONE);
        expressButtonTextView.setVisibility(View.GONE);

        isActionsView = false;

        addManyIncome.setExtended(false);
        incomeLinearLayout.setVisibility(View.VISIBLE);

        addManyIncome.setOnClickListener(view -> {
            if (!isActionsView){
                incomButton.show();
                incomButtonTopTexView.setVisibility(View.VISIBLE);
                expressButton.show();
                expressButtonTextView.setVisibility(View.VISIBLE);

                addManyIncome.setExtended(true);
                isActionsView = true;

            }else {

                incomButton.hide();
                expressButton.hide();
                expressButtonTextView.setVisibility(View.GONE);
                incomButtonTopTexView.setVisibility(View.GONE);

                addManyIncome.setExtended(false);
                isActionsView = false;
            }


        });



        // Tab Button Work ============= list view expense and income =========================
        ButtonTabIncome.setTextColor(getResources().getColor(R.color.ColorActive));
        ButtonTabExpress.setTextColor(getResources().getColor(R.color.white));

        ButtonTabIncome.setOnClickListener(view -> {



//            frameLayout.setVisibility(View.GONE);
            expenseViewListLinearLayout.setVisibility(View.GONE);
            incomeLinearLayout.setVisibility(View.VISIBLE);



            ButtonTabIncome.setTextColor(getResources().getColor(R.color.ColorActive));
            ButtonTabExpress.setTextColor(getResources().getColor(R.color.white));

        });

        ButtonTabExpress.setOnClickListener(view -> {

            incomeLinearLayout.setVisibility(View.GONE);
            expenseViewListLinearLayout.setVisibility(View.VISIBLE);
            loadExpenseData();
//            frameLayout.setVisibility(View.VISIBLE);

//
//                FragmentManager fragmentManager = getChildFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.myFrameLayout, new ExpenseList());
//                fragmentTransaction.commit();


            ButtonTabExpress.setTextColor(getResources().getColor(R.color.ColorActive));
            ButtonTabIncome.setTextColor(getResources().getColor(R.color.white));
        });





        databaseHelper = new DatabaseHelper(getContext());
        totalExpenseVar = ""+databaseHelper.calculateTotalExpense();
        totalIncomeVar = ""+databaseHelper.calculateTotalInCome();
        loadData();

        // Alertdialog show when click this button ==============================
        incomButton.setOnClickListener(view -> {




                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


                builder.setTitle("Create");
                View viewDialog = getLayoutInflater().inflate(R.layout.income_create, null);


                builder.setView(viewDialog);
                alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextInputEditText titleEd,where,product;
                titleEd = (TextInputEditText) viewDialog.findViewById(R.id.titleEd);
                where = (TextInputEditText) viewDialog.findViewById(R.id.whereEd);
                product = (TextInputEditText) viewDialog.findViewById(R.id.productEd);

                Button dialogButtonCancle = (Button) viewDialog.findViewById(R.id.btn_dialog_cancle);
                dialogButtonCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                // Ok button ==================================
                Button dialogButtonOk = (Button) viewDialog.findViewById(R.id.btn_dialog);
//                dialogButtonOk.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public int hashCode() {
//                        return super.hashCode();
//                    }
//
//                    @Override
//                    public void onClick(View v) {
//
//                        String title = titleEd.getText().toString();
//                        String des = where.getText().toString();
//                        String status = product.getText().toString();
//                        Double priceDouble = Double.parseDouble(status);
//
//                        if (title.isEmpty()){
//                            titleEd.setError("Value is Emtiy");
//                        } else if (des.isEmpty()) {
//                            where.setError("Value is Emtiy");
//
//                        } else if (status.isEmpty()) {
//                            product.setError("Value is Emtiy");
//
//                        }else {
//
//                            LocalDateTime myDateObj = null;
//                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                                myDateObj = LocalDateTime.now();
//                                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mm:ss");
//                                formattedDate = myDateObj.format(myFormatObj);
//                            }
//
//                            databaseHelper = new DatabaseHelper(getContext());
//
//                            Boolean isCheck = databaseHelper.insertIncomeData(title, des, formattedDate, priceDouble );
//                            loadData();
//
//                            if (isCheck){
//                                alertDialog.dismiss();
//                                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
//                            }else {
//                                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//
//                    }
//                });

            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String title = titleEd.getText().toString();
                        String des = where.getText().toString();
                        String status = product.getText().toString();
                        boolean IncomeIs = true;

                        // Check for empty fields first
                        if (title.isEmpty()) {
                            titleEd.setError("Value is Empty");
                            return;
                        } else if (des.isEmpty()) {
                            where.setError("Value is Empty");
                            return;
                        } else if (status.isEmpty()) {
                            product.setError("Value is Empty");
                            return;
                        }

                        // Parse the price value
                        Double priceDouble;
                        try {
                            priceDouble = Double.parseDouble(status);
                        } catch (NumberFormatException e) {
                            product.setError("Invalid number");
                            return;
                        }

                        databaseHelper = new DatabaseHelper(getContext());

                        LocalDateTime myDateObj = null;
                        String formattedDate = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            myDateObj = LocalDateTime.now();
                            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE dd-MMMM-yyyy HH:mm:ss");
                            formattedDate = myDateObj.format(myFormatObj);
                        }

                        boolean isCheck;
                        if (IncomeIs) {
                            isCheck = databaseHelper.insertIncomeData(title, des, formattedDate, priceDouble);
                        } else {
                            isCheck = databaseHelper.insertIncomeData(title, des, formattedDate, priceDouble);
                        }

                        loadData();

                        if (isCheck) {
                            alertDialog.dismiss();
                            Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error inserting data", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // Log the exception
                        Log.e("DialogButtonOkClick", "Error in onClick", e);
                        Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            alertDialog.show();



            incomButton.hide();
            expressButton.hide();
            expressButtonTextView.setVisibility(View.GONE);
            incomButtonTopTexView.setVisibility(View.GONE);


            isActionsView = false;

            addManyIncome.setExtended(false);


        }); // income add end ========================================


        // add expense =============================================
        expressButton.setOnClickListener(view -> {

            createWallet(true);


        });










//               total Text View Add this Propartiy ==================================

                loadData();






        return myView;


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
            View view1 = layoutInflater.inflate(R.layout.income_express_list, null ,false);

            TextView titleTv, whereTv, priceTv, timeTv;
            ImageView deleteImageButton;

            titleTv = view1.findViewById(R.id.titleTv);
            whereTv = view1.findViewById(R.id.whereTv);
            priceTv = view1.findViewById(R.id.productTv);
            timeTv = view1.findViewById(R.id.TimeTv);
            deleteImageButton = view1.findViewById(R.id.deleteWallet);

            // Income =======================================
            hashMap = arrayList.get(i);
            String idWallet = hashMap.get("id");
            String title = hashMap.get("title");
            String whereWallet = hashMap.get("whare");
            String timeWallet = hashMap.get("time");
            String productPrice = hashMap.get("product");




            timeTv.setText(timeWallet);
            whereTv.setText(whereWallet);
            priceTv.setText("$ "+productPrice);
            titleTv.setText(title);

            deleteImageButton.setOnClickListener(view2 -> {

                int var = databaseHelper.deleteIncomeById(idWallet);
                loadData();
                if(var > 0){


                    Toast.makeText(getContext(), "Data Deleted Click wallet Tab and Refresh", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Deletion Error", Toast.LENGTH_SHORT).show();
                }

            });




            return view1;
        }
    }



    public class MyExpenseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList2.size();
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
            hashMap2 = arrayList2.get(i);
            String idExpense = hashMap2.get("idEx");
            String titleExpense = hashMap2.get("titleEx");
            String whereExpense = hashMap2.get("whareEx");
            String timeExpenseValue = hashMap2.get("timeEx");
            String amount = hashMap2.get("productEx");






            timeTv.setText(timeExpenseValue);
            whereTv.setText(whereExpense);
            priceTv.setText("$ "+amount);
            titleTv.setText(titleExpense);


            deleteImageButton.setOnClickListener(view2 -> {
                int var  = databaseHelper.deleteExpenseById(idExpense);
                loadExpenseData();

                if(var > 0){

                    Toast.makeText(getContext(), "Data Deleted Click wallet Tab and Refresh", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Deletion Error", Toast.LENGTH_SHORT).show();
                }
            });



            return view1;
        }
    }



    // ========== load data ====================================

    public void loadExpenseData() {

        Cursor cursor2 = databaseHelper.getAllDataExpense();
        arrayList2.clear();

        totalIncomeVar = ""+databaseHelper.calculateTotalInCome();
        totalExpenseVar = ""+databaseHelper.calculateTotalExpense();

        totalIncomeTV.setText("$ "+totalIncomeVar);
        mainCashTv.setText("$ "+(databaseHelper.calculateTotalInCome()-databaseHelper.calculateTotalExpense()));
        TotalExpenseTV.setText("$ "+totalExpenseVar);



        if (cursor2 != null && cursor2.getCount() > 0) {
            while (cursor2.moveToNext()) {
                int id = cursor2.getInt(0);
                String title = cursor2.getString(1);
                String description = cursor2.getString(2);
                String endDate = cursor2.getString(3);
                String productPrice = cursor2.getString(4);

                hashMap2 = new HashMap<>();
                hashMap2.put("idEx", "" + id);
                hashMap2.put("titleEx", title);
                hashMap2.put("whareEx", description);
                hashMap2.put("timeEx", endDate);
                hashMap2.put("productEx", productPrice);


                arrayList2.add(hashMap2);


            }

            ExpenseList.setAdapter(new MyExpenseAdapter());


        }
    }

    public void loadData(){

        Cursor cursor = databaseHelper.getAllDataIcome();
        arrayList.clear();

        totalIncomeVar = ""+databaseHelper.calculateTotalInCome();
        totalExpenseVar = ""+databaseHelper.calculateTotalExpense();

        totalIncomeTV.setText("$ "+totalIncomeVar);
        mainCashTv.setText("$ "+(databaseHelper.calculateTotalInCome()-databaseHelper.calculateTotalExpense()));
        TotalExpenseTV.setText("$ "+totalExpenseVar);




        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String endDate = cursor.getString(3);
                String productPrice = cursor.getString(4);

                hashMap = new HashMap<>();
                hashMap.put("id", ""+id);
                hashMap.put("title", title);
                hashMap.put("whare", description);
                hashMap.put("time", endDate);
                hashMap.put("product", productPrice);


                arrayList.add(hashMap);


            }

            listViewWallet.setAdapter(new MyAdapter());


        }

    }



//    }


























        // array list for first set
//        private ArrayList<BarEntry> getBarEntriesOne() {
//
//            // creating a new array list
//            barEntries = new ArrayList<>();
//
//            // adding new entry to our array list with bar
//            // entry and passing x and y axis value to it.
//            barEntries.add(new BarEntry(1f, 4));
//            barEntries.add(new BarEntry(2f, 6));
//            barEntries.add(new BarEntry(3f, 8));
//            barEntries.add(new BarEntry(4f, 2));
//            barEntries.add(new BarEntry(5f, 4));
//            barEntries.add(new BarEntry(6f, 1));
//            return barEntries;
//        }
//
//        // array list for second set.
//        private ArrayList<BarEntry> getBarEntriesTwo() {
//
//            // creating a new array list
//            barEntries = new ArrayList<>();
//
//            // adding new entry to our array list with bar
//            // entry and passing x and y axis value to it.
//            barEntries.add(new BarEntry(1f, 8));
//            barEntries.add(new BarEntry(2f, 12));
//            barEntries.add(new BarEntry(3f, 4));
//            barEntries.add(new BarEntry(4f, 1));
//            barEntries.add(new BarEntry(5f, 7));
//            barEntries.add(new BarEntry(6f, 3));
//            return barEntries;
//        }


        public void createWallet(Boolean ExpenseIs){

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


            builder.setTitle("Create");
            View viewDialog = getLayoutInflater().inflate(R.layout.income_create, null);


            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextInputEditText titleEd,where,product;
            titleEd = (TextInputEditText) viewDialog.findViewById(R.id.titleEd);
            where = (TextInputEditText) viewDialog.findViewById(R.id.whereEd);
            product = (TextInputEditText) viewDialog.findViewById(R.id.productEd);

            Button dialogButtonCancle = (Button) viewDialog.findViewById(R.id.btn_dialog_cancle);
            dialogButtonCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            // Ok button ==================================
            Button dialogButtonOk = (Button) viewDialog.findViewById(R.id.btn_dialog);
//            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public int hashCode() {
//                    return super.hashCode();
//                }
//
//                @Override
//                public void onClick(View v) {
//
//                    String title = titleEd.getText().toString();
//                    String des = where.getText().toString();
//                    String status = product.getText().toString();
//                    Double priceDouble = Double.parseDouble(status);
//
//                    if (title.isEmpty()){
//                        titleEd.setError("Value is Emtiy");
//                    } else if (des.isEmpty()) {
//                        where.setError("Value is Emtiy");
//
//                    } else if (status.isEmpty()) {
//                        product.setError("Value is Emtiy");
//
//                    }else {
//
//                        if (ExpenseIs) {
//
//                            databaseHelper = new DatabaseHelper(getContext());
//
//
//                            LocalDateTime myDateObj = null;
//                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                                myDateObj = LocalDateTime.now();
//                                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE dd-MMMM-yyyy HH:mm:ss");
//                                formattedDate = myDateObj.format(myFormatObj);
//                            }
//
//
//
//                            Boolean isCheck = databaseHelper.insertExpenseData(title, des, formattedDate, priceDouble);
//                            loadData();
//                            if (isCheck) {
//                                alertDialog.dismiss();
//
//
//                                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
//                            }
//                        }else {
//                            databaseHelper = new DatabaseHelper(getContext());
//
//                            Boolean isCheck = databaseHelper.insertExpenseData(title, des,formattedDate, priceDouble);
//                            if (isCheck) {
//                                alertDialog.dismiss();
//                                loadData();
//
//                                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        }
//
//                    }
//
//                }
//            });

            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String title = titleEd.getText().toString();
                        String des = where.getText().toString();
                        String status = product.getText().toString();

                        // Check for empty fields first
                        if (title.isEmpty()) {
                            titleEd.setError("Value is Empty");
                            return;
                        } else if (des.isEmpty()) {
                            where.setError("Value is Empty");
                            return;
                        } else if (status.isEmpty()) {
                            product.setError("Value is Empty");
                            return;
                        }

                        // Parse the price value
                        Double priceDouble;
                        try {
                            priceDouble = Double.parseDouble(status);
                        } catch (NumberFormatException e) {
                            product.setError("Invalid number");
                            return;
                        }

                        databaseHelper = new DatabaseHelper(getContext());

                        LocalDateTime myDateObj = null;
                        String formattedDate = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            myDateObj = LocalDateTime.now();
                            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE dd-MMMM-yyyy HH:mm:ss");
                            formattedDate = myDateObj.format(myFormatObj);
                        }

                        boolean isCheck;
                        if (ExpenseIs) {
                            isCheck = databaseHelper.insertExpenseData(title, des, formattedDate, priceDouble);
                        } else {
                            isCheck = databaseHelper.insertExpenseData(title, des, formattedDate, priceDouble);
                        }

                        loadData();

                        if (isCheck) {
                            alertDialog.dismiss();
                            Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error inserting data", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // Log the exception
                        Log.e("DialogButtonOkClick", "Error in onClick", e);
                        Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            alertDialog.show();



            incomButton.hide();
            expressButton.hide();
            expressButtonTextView.setVisibility(View.GONE);
            incomButtonTopTexView.setVisibility(View.GONE);


            isActionsView = false;

            addManyIncome.setExtended(false);



        }
}