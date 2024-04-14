package com.example.homefoodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.homefoodapp.Adapter.FoodAdapter;
import com.example.homefoodapp.Domain.Foods;
import com.example.homefoodapp.databinding.ActivityMainScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class Main_screen extends AppCompatActivity {

    TextView adress;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    FirebaseDatabase database;
    String userId;


    private ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
        initFoodLoad();
        onSoup();
        onFish();
        onMeat();
        onOat();
        onProfile();
        onHome();
        onCart();

        adress = findViewById(R.id.adress);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                adress.setText(documentSnapshot.getString("Address"));
            }
        });


        binding.adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_screen.this, Adress_screen.class);
                startActivity(intent);

            }
        });
    }


    private void setVariable() {
        binding.profile.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Main_screen.this, Login_screen.class));
        });
    }

    private void onSoup() {
        binding.soup.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Soup_screen.class));
        });
    }
    private void onProfile() {
        binding.profile.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Profile_Screen.class));
        });
        binding.profile2.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Profile_Screen.class));
        });
    }

    private void onHome() {
        binding.home.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Main_screen.class));
        });
    }
    private void onCart() {
        binding.order.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Cart_Screen.class));
        });
    }
    private void onOat() {
        binding.oat.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Oat_screen.class));
        });
    }
    private void onMeat() {
        binding.meat.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Meat_screen.class));
        });
    }
    private void onFish() {
        binding.fish.setOnClickListener(v -> {
            startActivity(new Intent(Main_screen.this, Fish_screen.class));
        });
    }

    private void initFoodLoad(){

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Foods/Foods");
        binding.progressBar.setVisibility(View.VISIBLE);
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
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(Main_screen.this, LinearLayoutManager.HORIZONTAL, false));
                        RecyclerView.Adapter adapter = new FoodAdapter(list);
                        binding.recyclerView.setAdapter(adapter);

                    }
                        binding.progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
