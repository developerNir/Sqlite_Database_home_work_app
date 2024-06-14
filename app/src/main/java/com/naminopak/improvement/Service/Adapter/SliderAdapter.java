package com.naminopak.improvement.Service.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.naminopak.improvement.R;

public class SliderAdapter extends PagerAdapter {


    private Context context;
    LayoutInflater layoutInflater;


    public SliderAdapter(Context context) {
        this.context = context;
    }

    // Image array ---------------------------------------
    int Images[] = {
            R.drawable.note,
            R.drawable.todo,
            R.drawable.income,
            R.drawable.dream,

    };

    // header array ----------------------------------------
    int Headers[] ={
            R.string.headerOne,
            R.string.headerTwo,
            R.string.headerThree,
            R.string.headerFour
    };

    // description array ------------------------------------
    int Descriptions[] ={
            R.string.desOne,
            R.string.desTwo,
            R.string.desThree,
            R.string.desFour
    };

    @Override
    public int getCount() {
        return Headers.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        // kon layout slide korba naminopak LinearLayout ----------------------
        return view == (LinearLayout) object;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        // Inflater view Create ---------------------------------------
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        // element introduce -----------------------------------
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        TextView des = view.findViewById(R.id.TextDescription);

        imageView.setImageResource(Images[position]);
        textView.setText(Headers[position]);
        des.setText(Descriptions[position]);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {



        // kon layout slide korba naminopak LinearLayout ----------------------
        container.removeView((LinearLayout) object );
    }
}
