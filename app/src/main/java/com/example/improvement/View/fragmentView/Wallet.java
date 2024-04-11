package com.example.improvement.View.fragmentView;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.improvement.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class Wallet extends Fragment {

    // variable for our bar chart
    private BarChart barChart;

    // variable for our bar data set.
    private BarDataSet barDataSet1, barDataSet2;

    // array list for storing entries.
    private ArrayList barEntries;

    // creating a string array for displaying days.
    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};











    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_wallet, container, false);
































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




























        return myView;


    }






























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


}