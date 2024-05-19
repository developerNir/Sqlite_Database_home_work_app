package com.example.improvement.View.activityView;

import android.annotation.SuppressLint;
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
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    public static FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    MaterialToolbar materialToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        materialToolbar = findViewById(R.id.materialToolbar);



        replaceFragment(new Home());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                if (itemId == R.id.navHome){
                    materialToolbar.setTitle("Home");
                    replaceFragment(new Home());
                    return true;
                } else if (itemId == R.id.navNote) {
                    materialToolbar.setTitle("Note");
                    replaceFragment(new Note());
                    return true;
                }else if (itemId == R.id.navWallet) {
                    materialToolbar.setTitle("Wallet");
                    replaceFragment(new Wallet());
                }else if (itemId == R.id.navTodo) {
                    materialToolbar.setTitle("Todo");
                    replaceFragment(new Todo());
                    return true;
                } else if (itemId == R.id.navInventory) {
                    materialToolbar.setTitle("My Dream");
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