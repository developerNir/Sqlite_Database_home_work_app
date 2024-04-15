package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

public class Wallet extends Fragment {

    // variable for our bar chart
    private BarChart barChart;

    // variable for our bar data set.
    private BarDataSet barDataSet1, barDataSet2;

    // array list for storing entries.
    private ArrayList barEntries;

    // creating a string array for displaying days.
    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};



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
    HashMap<String,String> hashMap;
    FrameLayout frameLayout;




    private DatabaseHelper databaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_wallet, container, false);


        frameLayout = myView.findViewById(R.id.myFrameLayout);
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

        // List View Gone and Visible ================================
        incomeLinearLayout = myView.findViewById(R.id.incomeLinearLayout);

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



            frameLayout.setVisibility(View.GONE);
            incomeLinearLayout.setVisibility(View.VISIBLE);



            ButtonTabIncome.setTextColor(getResources().getColor(R.color.ColorActive));
            ButtonTabExpress.setTextColor(getResources().getColor(R.color.white));

        });

        ButtonTabExpress.setOnClickListener(view -> {

            incomeLinearLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);


                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.myFrameLayout, new ExpenseList());
                fragmentTransaction.commit();


            ButtonTabExpress.setTextColor(getResources().getColor(R.color.ColorActive));
            ButtonTabIncome.setTextColor(getResources().getColor(R.color.white));
        });





        databaseHelper = new DatabaseHelper(getContext());

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
                dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }

                    @Override
                    public void onClick(View v) {

                        String title = titleEd.getText().toString();
                        String des = where.getText().toString();
                        String status = product.getText().toString();
                        Double priceDouble = Double.parseDouble(status);

                        if (title.length()==0){
                            titleEd.setError("Value is Emtiy");
                        } else if (des.length() == 0) {
                            where.setError("Value is Emtiy");

                        } else if (status.length() == 0 ) {
                            product.setError("Value is Emtiy");

                        }else {

                            databaseHelper = new DatabaseHelper(getContext());

                            Boolean isCheck = databaseHelper.insertIncomeData(title, des, "Monday 02 April", priceDouble );
                            if (isCheck){
                                alertDialog.dismiss();



                                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
                            }

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























                // initializing variable for bar chart.
                barChart = myView.findViewById(R.id.idBarChart);

                // creating a new bar data set.
                barDataSet1 = new BarDataSet(getBarEntriesOne(), "Income");
                barDataSet1.setColor(getContext().getResources().getColor(R.color.primaryColor));
                barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Expenses");
                barDataSet2.setColor(Color.BLUE);

                // below line is to add bar data set to our bar data.
                BarData data = new BarData(barDataSet1, barDataSet2);

                // after adding data to our bar data we
                // are setting that data to our bar chart.
                barChart.setData(data);

                // below line is to remove description
                // label of our bar chart.
                barChart.getDescription().setEnabled(false);

                // below line is to get x axis
                // of our bar chart.
                XAxis xAxis = barChart.getXAxis();

                // below line is to set value formatter to our x-axis and
                // we are adding our days to our x axis.
                xAxis.setValueFormatter(new IndexAxisValueFormatter(days));

                // below line is to set center axis
                // labels to our bar chart.
                xAxis.setCenterAxisLabels(true);

                // below line is to set position
                // to our x-axis to bottom.
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                // below line is to set granularity
                // to our x axis labels.
                xAxis.setGranularity(1);

                // below line is to enable
                // granularity to our x axis.
                xAxis.setGranularityEnabled(true);

                // below line is to make our
                // bar chart as draggable.
                barChart.setDragEnabled(true);

                // below line is to make visible
                // range for our bar chart.
                barChart.setVisibleXRangeMaximum(3);

                // below line is to add bar
                // space to our chart.
                float barSpace = 0.1f;

                // below line is use to add group
                // spacing to our bar chart.
                float groupSpace = 0.5f;

                // we are setting width of
                // bar in below line.
                data.setBarWidth(0.15f);

                // below line is to set minimum
                // axis to our chart.
                barChart.getXAxis().setAxisMinimum(0);

                // below line is to
                // animate our chart.
                barChart.animate();

                // below line is to group bars
                // and add spacing to it.
                barChart.groupBars(0, groupSpace, barSpace);

                // below line is to invalidate
                // our bar chart.
                barChart.invalidate();







//               total Text View Add this Propartiy ==================================
                Cursor cursor = databaseHelper.getAllDataIcome();
                loadData(cursor);

                totalIncomeTV.setText("$ "+databaseHelper.calculateTotalInCome());
                mainCashTv.setText("$ "+(databaseHelper.calculateTotalInCome()-databaseHelper.calculateTotalExpense()));
                TotalExpenseTV.setText("$ "+databaseHelper.calculateTotalExpense());
















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
        private ArrayList<BarEntry> getBarEntriesOne() {

            // creating a new array list
            barEntries = new ArrayList<>();

            // adding new entry to our array list with bar
            // entry and passing x and y axis value to it.
            barEntries.add(new BarEntry(1f, 4));
            barEntries.add(new BarEntry(2f, 6));
            barEntries.add(new BarEntry(3f, 8));
            barEntries.add(new BarEntry(4f, 2));
            barEntries.add(new BarEntry(5f, 4));
            barEntries.add(new BarEntry(6f, 1));
            return barEntries;
        }

        // array list for second set.
        private ArrayList<BarEntry> getBarEntriesTwo() {

            // creating a new array list
            barEntries = new ArrayList<>();

            // adding new entry to our array list with bar
            // entry and passing x and y axis value to it.
            barEntries.add(new BarEntry(1f, 8));
            barEntries.add(new BarEntry(2f, 12));
            barEntries.add(new BarEntry(3f, 4));
            barEntries.add(new BarEntry(4f, 1));
            barEntries.add(new BarEntry(5f, 7));
            barEntries.add(new BarEntry(6f, 3));
            return barEntries;
        }


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
            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }

                @Override
                public void onClick(View v) {

                    String title = titleEd.getText().toString();
                    String des = where.getText().toString();
                    String status = product.getText().toString();
                    Double priceDouble = Double.parseDouble(status);

                    if (title.length()==0){
                        titleEd.setError("Value is Emtiy");
                    } else if (des.length() == 0) {
                        where.setError("Value is Emtiy");

                    } else if (status.length() == 0 ) {
                        product.setError("Value is Emtiy");

                    }else {

                        if (ExpenseIs) {
                            databaseHelper = new DatabaseHelper(getContext());

                            Boolean isCheck = databaseHelper.insertExpenseData(title, des, "Monday 02 April", priceDouble);
                            if (isCheck) {
                                alertDialog.dismiss();


                                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            databaseHelper = new DatabaseHelper(getContext());

                            Boolean isCheck = databaseHelper.insertExpenseData(title, des, "Monday 02 April", priceDouble);
                            if (isCheck) {
                                alertDialog.dismiss();


                                Toast.makeText(getContext(), "Data inserted ...", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "get an Error", Toast.LENGTH_SHORT).show();
                            }


                        }

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