package com.example.homefoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homefoodapp.databinding.ActivitySideMenuScreenBinding;
import com.google.firebase.auth.FirebaseAuth;


public class SideMenu_screen extends AppCompatActivity {
    private ActivitySideMenuScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySideMenuScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}