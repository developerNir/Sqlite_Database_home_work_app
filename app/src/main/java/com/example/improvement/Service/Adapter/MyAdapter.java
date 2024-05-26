package com.example.improvement.Service.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvement.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.blog_card_item, parent, false);
            return new myViewHolder(view);
        }



    @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {




        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public class myViewHolder extends RecyclerView.ViewHolder{

            TextView desTv;
            CardView cardView;
            ImageView imageView;
            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                desTv = itemView.findViewById(R.id.titleEd);
                imageView = itemView.findViewById(R.id.imageView);
                cardView = itemView.findViewById(R.id.cardView);
            }
        }

}
