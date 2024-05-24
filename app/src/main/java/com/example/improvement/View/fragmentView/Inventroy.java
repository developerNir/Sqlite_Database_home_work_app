package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.improvement.R;
import com.example.improvement.View.fragmentView.Dream.DreamTab;
import com.example.improvement.View.fragmentView.Dream.RoutineTab;
import com.google.android.material.tabs.TabLayout;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Inventroy extends Fragment {


    TabLayout tabLayout;
    FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_inventroy, container, false);


        tabLayout = myView.findViewById(R.id.tabLayout);

        frameLayout = myView.findViewById(R.id.dreamFramLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();

                if (tabPosition == 0){
                    replaceFragment(new DreamTab());
                    Toast.makeText(getContext(), "Dream", Toast.LENGTH_SHORT).show();
                } else if (tabPosition == 1) {
                    replaceFragment(new RoutineTab());
                    Toast.makeText(getContext(), "Routine", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });







        return myView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dreamFramLayout, fragment);
        fragmentTransaction.commit();

    }

}