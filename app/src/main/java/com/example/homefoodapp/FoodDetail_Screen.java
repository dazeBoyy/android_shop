package com.example.homefoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.homefoodapp.Domain.Foods;
import com.example.homefoodapp.Helper.ManagmentCart;
import com.example.homefoodapp.databinding.ActivityFoodDetailScreenBinding;

public class FoodDetail_Screen extends AppCompatActivity {

    ActivityFoodDetailScreenBinding binding;
    private Foods object;
    private int num = 1;

    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable(){
        managmentCart = new ManagmentCart(this);
        binding.backbtn.setOnClickListener(v -> finish());

        Glide.with(FoodDetail_Screen.this)
                .load(object.getImagePath())
                .into(binding.pic);

        binding.Title.setText(object.getTitle());
        binding.price.setText(object.getPrice() + " руб.");
        binding.description.setText(object.getDescription());
        binding.name.setText(object.getPerson());
        binding.rate.setText(""+ object.getStar());

        binding.plus.setOnClickListener(v -> {
                num=num+1;
                binding.numTxt.setText(String.valueOf(num));

        });
        binding.minus.setOnClickListener(v -> {
                if (num>1){
                    num=num-1;
                    binding.numTxt.setText(String.valueOf(num));
            }
        });
        binding.add.setOnClickListener(v ->{
               object.setNumberInCart(num);
               managmentCart.insertFood(object);
        });
    }

    private void getIntentExtra(){
        object= (Foods) getIntent().getSerializableExtra("object");
    }
}