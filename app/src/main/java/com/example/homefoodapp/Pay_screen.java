package com.example.homefoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homefoodapp.databinding.ActivityFishScreenBinding;
import com.example.homefoodapp.databinding.ActivityPayScreenBinding;

public class Pay_screen extends AppCompatActivity {

    private ActivityPayScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_screen);

        binding = ActivityPayScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onPay();
        onMain();
    }


    private void onPay() {
        binding.pay.setOnClickListener(v -> {
            startActivity(new Intent(Pay_screen.this, Main_screen.class));
        });
    }



    private void onMain() {
        binding.skip.setOnClickListener(v -> finish());
    }
}