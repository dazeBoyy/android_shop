package com.example.homefoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homefoodapp.databinding.ActivityAboutScreenBinding;
import com.example.homefoodapp.databinding.ActivityPayScreenBinding;

public class About_screen extends AppCompatActivity {
    private ActivityAboutScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        binding = ActivityAboutScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onBack();
    }


    private void onBack() {
        binding.skip.setOnClickListener(v -> finish());
    }
}