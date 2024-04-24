package com.example.homefoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.homefoodapp.Adapter.FoodAdapter;
import com.example.homefoodapp.Domain.Foods;
import com.example.homefoodapp.databinding.ActivityFavoriteScreenBinding;
import com.example.homefoodapp.databinding.ActivityMeatScreenBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Favorite_screen extends AppCompatActivity {

    FirebaseDatabase database;


    private ActivityFavoriteScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_screen);

        binding = ActivityFavoriteScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDataLoad();
        onMain();
    }


    private void initDataLoad(){

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Foods/Foods");
        binding.progressBar3.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        binding.listOfFavorites.setLayoutManager(new LinearLayoutManager(Favorite_screen.this, LinearLayoutManager.VERTICAL, false));
                        RecyclerView.Adapter adapter = new FoodAdapter(list);
                        binding.listOfFavorites.setAdapter(adapter);

                    }
                    binding.progressBar3.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void onMain() {
        binding.skip.setOnClickListener(v -> {
            startActivity(new Intent(Favorite_screen.this, Main_screen.class));
        });
    }
}