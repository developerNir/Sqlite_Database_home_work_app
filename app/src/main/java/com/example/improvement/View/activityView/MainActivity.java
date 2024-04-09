package com.example.improvement.View.activityView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.improvement.R;
import com.example.improvement.View.fragmentView.Home;
import com.example.improvement.View.fragmentView.Inventroy;
import com.example.improvement.View.fragmentView.Note;
import com.example.improvement.View.fragmentView.Todo;
import com.example.improvement.View.fragmentView.Wallet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        replaceFragment(new Home());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                if (itemId == R.id.navHome){
                    replaceFragment(new Home());
                    return true;
                } else if (itemId == R.id.navNote) {
                    replaceFragment(new Note());
                    return true;
                }else if (itemId == R.id.navWallet) {
                    replaceFragment(new Wallet());
                }else if (itemId == R.id.navTodo) {
                    replaceFragment(new Todo());
                    return true;
                } else if (itemId == R.id.navInventory) {
                    replaceFragment(new Inventroy());
                    return true;
                }

                return true;
            }
        });







    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }



}