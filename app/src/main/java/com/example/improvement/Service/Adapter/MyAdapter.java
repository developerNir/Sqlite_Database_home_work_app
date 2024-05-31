package com.example.improvement.Service.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvement.R;
import com.example.improvement.Service.Model.DreamModel;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {




    private Context mContext;
    private ArrayList<DreamModel> mList;

    public MyAdapter(Context mContext, ArrayList<DreamModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.blog_card_item, parent, false);
            return new myViewHolder(view);
        }



    @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {






//            holder.desTv.setText(mList.get(position).getTitle());
//            holder.cardView.setOnClickListener(view -> {
//                Toast.makeText(mContext, ""+mList.get(position).getDes(), Toast.LENGTH_SHORT).show();
//            });
////            holder.imageView.setImageBitmap();



        }

        @Override
        public int getItemCount() {
            return mList.size();
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
