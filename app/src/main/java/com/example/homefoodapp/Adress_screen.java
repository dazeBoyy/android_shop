package com.example.homefoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.homefoodapp.databinding.ActivityAdressScreenBinding;
import com.example.homefoodapp.databinding.ActivityRegistrationScreenBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Adress_screen extends AppCompatActivity {
    private ActivityAdressScreenBinding binding;
    public static final String TAG = "ContentValues";
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress_screen);
        binding = ActivityAdressScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        String userId = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);


        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Adress_screen.this, Main_screen.class);

                String address = binding.adress.getText().toString();
                if (!address.isEmpty()) {
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("Address", address);

                    documentReference.update(userData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Добавлен адрес для : " + userId);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "Ошибка добавления ареса: " + userId, e);
                                }
                            });
                }else{
                    Toast.makeText(Adress_screen.this, "Введите адрес", Toast.LENGTH_SHORT).show();
                }
            }
        });
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists() && documentSnapshot.contains("Address")) {
                    binding.skip.setVisibility(View.VISIBLE);
                    binding.skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Adress_screen.this, Main_screen.class));
                        }
                    });
                } else {
                    binding.skip.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Ошибка", e);
            }
        });

    }
}