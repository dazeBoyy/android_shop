package com.example.homefoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homefoodapp.databinding.ActivityOrdersScreenBinding;
import com.example.homefoodapp.databinding.ActivityPayScreenBinding;

public class Orders_screen extends AppCompatActivity {
    private ActivityOrdersScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_screen);


        binding = ActivityOrdersScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onBack();
    }



    private void onBack() {
        binding.skip.setOnClickListener(v -> finish());
    }
}