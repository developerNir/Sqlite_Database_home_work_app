package com.example.improvement.View.fragmentView.Dream;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.improvement.R;
import com.google.android.material.button.MaterialButton;


public class DreamTab extends Fragment {

    MaterialButton addDreamBtn;
    LinearLayout addDreamLayout;
    RecyclerView recyclerView;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_dream_tab, container, false);


        addDreamBtn = myView.findViewById(R.id.addDreamBtn);
        addDreamLayout = myView.findViewById(R.id.addDreamLayout);
        recyclerView = myView.findViewById(R.id.recyclerView);



        addDreamBtn.setOnClickListener(view -> {

            recyclerView.setVisibility(View.GONE);

            if (addDreamBtn.getVisibility() == View.VISIBLE){
                addDreamLayout.setVisibility(View.GONE);
                addDreamBtn.setIcon(getResources().getDrawable(R.drawable.baseline_check_24));

            }

            if (addDreamBtn.getVisibility() == View.GONE)
            {
                addDreamLayout.setVisibility(View.VISIBLE);
                addDreamBtn.setIcon(getResources().getDrawable(R.drawable.add_ic));

            }
        });



        return myView;
    }
}