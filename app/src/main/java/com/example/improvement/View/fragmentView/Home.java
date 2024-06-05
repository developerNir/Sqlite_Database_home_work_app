package com.example.improvement.View.fragmentView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.improvement.R;
import com.example.improvement.Service.Adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Home extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter myAdapter;
    Context context = getContext();

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = myView.findViewById(R.id.recyclerView);



        hashMap = new HashMap<>();
        hashMap.put("id", "1");
        hashMap.put("image", "https://res.cloudinary.com/dwlcudfef/image/upload/v1715218902/icons/hcspubfb3titsq2ojrj2.png");
        hashMap.put("text", "News");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("id", "2");
        hashMap.put("image", "https://res.cloudinary.com/dwlcudfef/image/upload/v1715221364/icons/tzw5qvycyiy1xaqwehfh.png");
        hashMap.put("text", "Education");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("id", "3");
        hashMap.put("image", "https://res.cloudinary.com/dwlcudfef/image/upload/v1715256380/icons/ti2riptmdjlwatvyjn6j.png");
        hashMap.put("text", "Call Book");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("id", "3");
        hashMap.put("image", "https://res.cloudinary.com/dwlcudfef/image/upload/v1715256380/icons/ti2riptmdjlwatvyjn6j.png");
        hashMap.put("text", "Productivity");
        arrayList.add(hashMap);





        myAdapter = new HomeAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));













        return myView;
    }


    // adapter create ==============================
//    private class MyAdapter extends BaseAdapter {
//
//        private Context context;
//
//        public MyAdapter(Context context) {
//            this.context = context;
//        }
//        @Override
//        public int getCount() {
//            return arrayList.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @SuppressLint("MissingInflatedId")
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            ImageView imageView;
//            TextView textView;
//            CardView cardView;
//
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewHolder = layoutInflater.inflate(R.layout.dash_item, viewGroup, false);
//
//            imageView = viewHolder.findViewById(R.id.dash_image);
//            textView = viewHolder.findViewById(R.id.dash_text);
//            cardView = viewHolder.findViewById(R.id.cardView);
//
//
//
//            hashMap = arrayList.get(i);
//            String id = hashMap.get("id");
//            String image = hashMap.get("image");
//            String text = hashMap.get("text");
//
//
//            if (id.equals("1")){
//                cardView.setOnClickListener(view1 -> {
//                    startActivity(new Intent(context, BusinessInfo.class));
//                });
//            }
//            if (id.equals("2")){
//                cardView.setOnClickListener(view1 -> {
//                    Toast.makeText(context, "this 2 item", Toast.LENGTH_SHORT).show();
//                });
//            }
//            if (id.equals("3")){
//                cardView.setOnClickListener(view1 -> {
////                    startActivity(new Intent(context, CallListView.class));
//                });
//            }
//
//
//
//
//            Picasso.get().load(image).into(imageView);
//            textView.setText(text);
//
//
//            return viewHolder;
//        }
//    }




}