package com.example.homefoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.homefoodapp.Domain.Foods;
import com.example.homefoodapp.Helper.ManagmentCart;
import com.example.homefoodapp.databinding.ActivityFoodDetailScreenBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodDetail_Screen extends AppCompatActivity {

    ActivityFoodDetailScreenBinding binding;
    private Foods object;
    private int num = 1;

    private DatabaseReference mDatabase;

    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
        setFavorite();


    }

    private void setFavorite() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Foods").child("Foods").child(String.valueOf(object.getId()));

        binding.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Получаем текущее значение поля BestFood
                        Boolean currentValue = dataSnapshot.child("BestFood").getValue(Boolean.class);
                        // Инвертируем его значение (true -> false, false -> true)
                        Boolean newValue = !currentValue;
                        // Обновляем значение поля BestFood в базе данных
                        mDatabase.child("BestFood").setValue(newValue);
                        if (newValue) {
                            // Если BestFood стало true, устанавливаем картинку favorite_true
                            binding.favorite.setImageResource(R.drawable.favorite_true);
                        } else {
                            // Если BestFood стало false, устанавливаем картинку favorite_false
                            binding.favorite.setImageResource(R.drawable.favorite_false);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("FOOD DETAIL", "Тут слегка ошибка");
                    }

                });
            }
        });
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

        if (object.isBestFood()) {
            // Если BestFood стало true, устанавливаем картинку favorite_true
            binding.favorite.setImageResource(R.drawable.favorite_true);
        } else {
            // Если BestFood стало false, устанавливаем картинку favorite_false
            binding.favorite.setImageResource(R.drawable.favorite_false);
        }


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