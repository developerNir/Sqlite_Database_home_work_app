package com.example.improvement.Service.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.improvement.R;

public class CalenderViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView dayOfMonth;
    private final CalenderAdapter.OnItemListener onItemListener;


    public CalenderViewHoder(View itemView, CalenderAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.textView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
