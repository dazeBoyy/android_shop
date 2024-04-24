package com.example.homefoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.homefoodapp.databinding.ActivityMainScreenBinding;
import com.example.homefoodapp.databinding.ActivityRecoveryScreenBinding;

public class Recovery_screen extends AppCompatActivity {
    private ActivityRecoveryScreenBinding binding;
    private String mailField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecoveryScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailField = binding.mail.getText().toString();
                if(mailField.isEmpty() != true){
                    Intent intent = new Intent(Recovery_screen.this, Login_screen.class);
                    startActivity(intent);
                }

            }
        });


    }

}