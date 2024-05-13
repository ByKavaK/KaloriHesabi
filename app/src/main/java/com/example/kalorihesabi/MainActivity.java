package com.example.kalorihesabi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity
{
    DatabaseReference databaseReference;
    BottomNavigationView bottomNavi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavi=findViewById(R.id.naviBar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentler,new FragmentSabah()).commit();

        bottomNavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.sabah) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new FragmentSabah()).addToBackStack(null).commit();
                } else if (item.getItemId() == R.id.ogle) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new FragmentOgle()).addToBackStack(null).commit();
                } else if (item.getItemId() == R.id.aksam) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new FragmentAksam()).addToBackStack(null).commit();
                } else if (item.getItemId() == R.id.bilgi) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new FragmentBilgi()).addToBackStack(null).commit();
                }
                return true;
            }
        });


    }
}