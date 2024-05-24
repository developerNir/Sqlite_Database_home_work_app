package com.example.improvement.View.fragmentView.Dream;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.improvement.R;


public class RoutineTab extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myViewOne = inflater.inflate(R.layout.fragment_routine_tab, container, false);



        return myViewOne;

    }
}