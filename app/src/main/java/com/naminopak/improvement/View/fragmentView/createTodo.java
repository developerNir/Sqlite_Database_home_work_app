package com.naminopak.improvement.View.fragmentView;



import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
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
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.Toast;

import com.naminopak.improvement.R;
import com.naminopak.improvement.Service.Database.todoDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class createTodo extends Fragment {


    String DropItem;
    String CreateMonth;
    String EndDate;
    String CreateDay;
    Button editTextDatePick;

    DatePickerDialog picker;
    private DatabaseHelper databaseHelper;
    FloatingActionButton cancel, create;
    TextInputEditText titleEd, DesEd;
    ConstraintLayout constraintLayout;
    Boolean isInserted;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> arrayAdapter;

    String formatData, EeeData, dayName;


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
        editTextDatePick = viewInflater.findViewById(R.id.eDateTv);

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



        // date picker EditText =======================================
        editTextDatePick.setOnClickListener(view3 -> {

            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(context,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            editTextDatePick.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);


                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);

                            // Format the date using SimpleDateFormat
                            SimpleDateFormat dateFormat = new SimpleDateFormat("E");
                            dayName = dateFormat.format(calendar.getTime());





                                CreateDay = dayOfMonth+"";

                                if(monthOfYear == 0){
                                    CreateMonth = "January "+dayOfMonth;

                                    Toast.makeText(getContext(), "January", Toast.LENGTH_SHORT).show();
                                }else if (monthOfYear == 1){
                                    CreateMonth = "February";

                                    Toast.makeText(getContext(), "February", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 2){
                                    CreateMonth = "Merch";

                                    Toast.makeText(getContext(), "Merch", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 3){
                                    CreateMonth = "April";

                                    Toast.makeText(getContext(), "April", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 4){
                                    CreateMonth = "May";

                                    Toast.makeText(getContext(), "May", Toast.LENGTH_SHORT).show();
                                }else if (monthOfYear == 5){
                                    CreateMonth = "June";

                                    Toast.makeText(getContext(), "June", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 6){
                                    CreateMonth = "July";

                                    Toast.makeText(getContext(), "July", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 7){
                                    CreateMonth = "August";

                                    Toast.makeText(getContext(), "August", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 8){
                                    CreateMonth = "September";

                                    Toast.makeText(getContext(), "September", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 9){
                                    CreateMonth = "October";

                                    Toast.makeText(getContext(), "October", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 10){
                                    CreateMonth = "November";

                                    Toast.makeText(getContext(), "November", Toast.LENGTH_SHORT).show();
                                }
                                else if (monthOfYear == 11){
                                    CreateMonth = "December";

                                    Toast.makeText(getContext(), "December", Toast.LENGTH_SHORT).show();
                                }

                        }
                    }, year, month, day);
            picker.show();
        });

        // data picker End =============================================





        create.setOnClickListener(view -> {

            try {

                String titleValue = titleEd.getText().toString();
                String desValue = DesEd.getText().toString();

                if (titleValue.isEmpty()){
                    titleEd.setError("Value is Emtiy");
                    return;
                } else if (desValue.isEmpty()) {
                    DesEd.setError("Null Value");
                    return;
                } else if (DropItem.length() == 0) {
                    Toast.makeText(context, "Please Status Select", Toast.LENGTH_SHORT).show();
                    return;
                } else {


                    if (CreateDay.length() == 1){
                        CreateDay = "0"+CreateDay;
                    }

                    LocalDateTime myDateObj = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        myDateObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E dd-MM-yyyy  HH:mm:ss");
                        DateTimeFormatter Eee = DateTimeFormatter.ofPattern("E");
                        formatData = myDateObj.format(myFormatObj);
                        EeeData = myDateObj.format(Eee);
                    }



                    // data insert ===================================
                    isInserted  = databaseHelper.insertData(titleValue, desValue, dayName+""+CreateDay+" "+CreateMonth, formatData, DropItem);

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
            }catch (Exception e){
                Toast.makeText(context, "Todo Create Failed", Toast.LENGTH_SHORT).show();
            }

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

    private String datePickerSetAndEnd(Context context){

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);



        // date picker dialog
        picker = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {



                        if(monthOfYear == 0){
                            EndDate = "January "+dayOfMonth;

                            Toast.makeText(getContext(), "January", Toast.LENGTH_SHORT).show();
                        }else if (monthOfYear == 1){
                            EndDate = "February "+dayOfMonth;

                            Toast.makeText(getContext(), "February", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 2){
                            EndDate = "Merch "+dayOfMonth;

                            Toast.makeText(getContext(), "Merch", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 3){
                            EndDate = "April "+dayOfMonth;

                            Toast.makeText(getContext(), "April", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 4){
                            EndDate = "May "+dayOfMonth;

                            Toast.makeText(getContext(), "May", Toast.LENGTH_SHORT).show();
                        }else if (monthOfYear == 5){
                            EndDate = "June "+dayOfMonth;
                            Toast.makeText(getContext(), "June", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 6){
                            EndDate = "July "+dayOfMonth;
                            Toast.makeText(getContext(), "July", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 7){
                            EndDate = "August "+dayOfMonth;

                            Toast.makeText(getContext(), "August", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 8){
                            EndDate = "September "+dayOfMonth;

                            Toast.makeText(getContext(), "September", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 9){
                            EndDate = "October "+dayOfMonth;

                            Toast.makeText(getContext(), "October", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 10){
                            EndDate = "November "+dayOfMonth;

                            Toast.makeText(getContext(), "November", Toast.LENGTH_SHORT).show();
                        }
                        else if (monthOfYear == 11){
                            EndDate = "December "+dayOfMonth;

                            Toast.makeText(getContext(), "December", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, year, month, day);
        picker.show();
        return EndDate;
    }



}