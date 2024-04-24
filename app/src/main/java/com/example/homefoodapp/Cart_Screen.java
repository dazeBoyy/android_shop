package com.example.homefoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.homefoodapp.Adapter.CartAdapter;
import com.example.homefoodapp.Helper.ChangeNumberItemsListener;
import com.example.homefoodapp.Helper.ManagmentCart;
import com.example.homefoodapp.databinding.ActivityCartScreenBinding;

public class Cart_Screen extends AppCompatActivity {
    private ActivityCartScreenBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartScreenBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        calculateCart();
        setVariable();
        initList();
        onPay();



    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        binding.cardview.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        binding.cardview.setAdapter(adapter);

    }

    private void calculateCart(){
        double tax = 0.2; // 20% налога
        double delivery = 0;

        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) /100;
        double total = itemTotal + (itemTotal * tax) + delivery;
        double tax_final = (itemTotal * tax);

        binding.total.setText(total + " руб.");
        binding.tax.setText(String.format("%.1f", tax_final) + " руб.");
        binding.items.setText(total + " руб.");
        binding.amount.setText("(" + String.valueOf(managmentCart.getItemsNumber()) + ")");
    }

    private void onPay() {
        binding.pay.setOnClickListener(v -> {
            startActivity(new Intent(Cart_Screen.this, Pay_screen.class));
        });
    }

    private  void  setVariable(){
        binding.backbtn.setOnClickListener(v -> finish());
    }
}