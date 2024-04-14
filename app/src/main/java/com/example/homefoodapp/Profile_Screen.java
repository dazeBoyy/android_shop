package com.example.homefoodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homefoodapp.databinding.ActivityMainScreenBinding;
import com.example.homefoodapp.databinding.ActivityProfileScreenBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class Profile_Screen extends AppCompatActivity {

    TextView mail,name;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    FirebaseDatabase database;
    String userId;

    public static final String TAG = "ContentValues";

    private ActivityProfileScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setExit();

        mail = findViewById(R.id.mail_profile);
        name = findViewById(R.id.name_profile);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                name.setText(documentSnapshot.getString("Name"));
                mail.setText(fAuth.getCurrentUser().getEmail());
            }
        });




        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.name.getText().toString();
                String phone = binding.phone.getText().toString();
                if (!name.isEmpty() && !phone.isEmpty()) {
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("Name", name);
                    userData.put("Phone", phone);

                    documentReference.update(userData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Имя было изменено и был добавлен номер телефона : " + userId);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "Ошибка добавления телефона или изменения имени: " + userId, e);
                                }
                            });
                }else{
                    Toast.makeText(Profile_Screen.this, "Введите адрес", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void setExit(){
        binding.backbtn.setOnClickListener(v -> finish());
    }
}