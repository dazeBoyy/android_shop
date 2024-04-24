package com.example.homefoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.homefoodapp.Adapter.FoodAdapter;
import com.example.homefoodapp.Domain.Foods;
import com.example.homefoodapp.databinding.ActivityMainScreenBinding;
import com.example.homefoodapp.databinding.ActivitySoupScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Soup_screen extends AppCompatActivity {

    FirebaseDatabase database;


    private ActivitySoupScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySoupScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initSoupLoad();
        onMain();

    }

    private void initSoupLoad(){

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Foods/Foods");
        binding.progressBar2.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query = myRef.orderByChild("Category").equalTo("Супы");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        binding.cards.setLayoutManager(new LinearLayoutManager(Soup_screen.this, LinearLayoutManager.VERTICAL, false));
                        RecyclerView.Adapter adapter = new FoodAdapter(list);
                        binding.cards.setAdapter(adapter);

                    }
                    binding.progressBar2.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void onMain() {
        binding.skip.setOnClickListener(v -> finish());
    }
}