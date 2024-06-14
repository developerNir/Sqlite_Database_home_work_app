package com.naminopak.improvement.View.activityView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import androidx.activity.OnBackPressedCallback;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.naminopak.improvement.R;
import com.naminopak.improvement.View.fragmentView.Developer;
import com.naminopak.improvement.View.fragmentView.Dream.DreamTab;
import com.naminopak.improvement.View.fragmentView.Home;
import com.naminopak.improvement.View.fragmentView.Note;
import com.naminopak.improvement.View.fragmentView.Todo;
import com.naminopak.improvement.View.fragmentView.Wallet;
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


        // Material toolbar ================================== icon Click Listener ======
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_developer){
                    replaceFragment(new Developer());
                    Toast.makeText(MainActivity.this, "Developer Option", Toast.LENGTH_SHORT).show();
                    materialToolbar.setTitle("Developer");
                }

                if (item.getItemId() == R.id.action_settings){
                    Toast.makeText(MainActivity.this, "Settings Option", Toast.LENGTH_SHORT).show();
                }

                if (item.getItemId() == R.id.action_more_apps){
                    Toast.makeText(MainActivity.this, "More Apps", Toast.LENGTH_SHORT).show();
                }

                if (item.getItemId() == R.id.action_rate_us){
                    Toast.makeText(MainActivity.this, "Rate Us", Toast.LENGTH_SHORT).show();
                }



                return false;
            }
        });





        // Navigation Item selected =============================
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
                    replaceFragment(new DreamTab());
                    return true;
                }

                return true;
            }
        });


        // On back pass action =============================== function
        setUpOnBackPressed();





    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }

    // On back pass action ===============================



    public void setUpOnBackPressed(){
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirm Exit")
                        .setMessage("Are you sure you want to exit?")
                        .setIcon(R.drawable.exit_icon)
                        .setCancelable(false)
                        .setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // No click and Worked this Code ===============
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("Yes, Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Yes click and Worked this Code ===============
                                dialogInterface.dismiss();
                                finishAndRemoveTask();
                            }
                        })
                        .show();


            }
        });
    }
    // OnBack passed end ===============================



}