package com.example.improvement.View.fragmentView.Dream;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.Service.Adapter.CalenderAdapter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class RoutineTab extends Fragment implements CalenderAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myViewOne = inflater.inflate(R.layout.fragment_routine_tab, container, false);




        calendarRecyclerView = myViewOne.findViewById(R.id.calendarRecyclerView);
        monthYearText = myViewOne.findViewById(R.id.monthYearTV);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = LocalDate.now();
        }
        setMonthView();




        return myViewOne;

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalenderAdapter calendarAdapter = new CalenderAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date)
    {

            ArrayList<String> daysInMonthArray = new ArrayList<>();
            YearMonth yearMonth = YearMonth.from(date);

            int daysInMonth = yearMonth.lengthOfMonth();

            LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
            int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

            for (int i = 1; i <= 42; i++) {
                if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                    daysInMonthArray.add("");
                } else {
                    daysInMonthArray.add(String.valueOf(i - dayOfWeek));
                }
            }
            return  daysInMonthArray;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = null;
            formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
            return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.minusMonths(1);
        }
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.plusMonths(1);
        }
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}