package com.example.improvement.Service.Adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvement.R;
import com.example.improvement.Service.Model.BusinessDataModel;
import com.example.improvement.View.activityView.BlogInfoDetails;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<BusinessDataModel> mListData;
    private Context context;

    public HomeAdapter(List<BusinessDataModel> mListData, Context context) {
        this.mListData = mListData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_of_blog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {




        BusinessDataModel item1 = mListData.get(position);
        String title = item1.getCOLUMN_TITEL();
        byte[] image = item1.getImage();
        String des = item1.getCOLUMN_DES();

        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);


        holder.linearLayout.setOnClickListener(view2 -> {

            BlogInfoDetails.bitmap = bmp;
            BlogInfoDetails.COLUMN_TITEL = title;
            BlogInfoDetails.COLUMN_DES = item1.getCOLUMN_DES();
            BlogInfoDetails.COLUMN_HONE = item1.getCOLUMN_HONE();
            BlogInfoDetails.COLUMN_DONE = item1.getCOLUMN_DONE();
            BlogInfoDetails.COLUMN_HTWO = item1.getCOLUMN_HTWO();
            BlogInfoDetails.COLUMN_DTWO = item1.getCOLUMN_DTWO();
            BlogInfoDetails.COLUMN_HTHREE = item1.getCOLUMN_HTHREE();
            BlogInfoDetails.COLUMN_DTHREE = item1.getCOLUMN_DTHREE();
            BlogInfoDetails.COLUMN_HFOUR = item1.getCOLUMN_HFOUR();
            BlogInfoDetails.COLUMN_DFOUR = item1.getCOLUMN_DFOUR();
            BlogInfoDetails.COLUMN_HFIVE = item1.getCOLUMN_HFIVE();
            BlogInfoDetails.COLUMN_DFIVE = item1.getCOLUMN_DFIVE();

            BlogInfoDetails.COLUMN_QAUTE = item1.getCOLUMN_QAUTE();

            Intent intent = new Intent(context, BlogInfoDetails.class);
            context.startActivity(intent);
        });




        holder.imageView.setImageBitmap(bmp);
        holder.titleTv.setText(title);
        holder.desTv.setText(des);






    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView desTv,titleTv;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.titleTv);
            desTv = itemView.findViewById(R.id.desTv);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }

    }


}
