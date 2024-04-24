package com.example.homefoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class Drawer_screen extends AppCompatActivity {
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_header);


        TextView button = findViewById(R.id.name);
        if (button != null) {

            Log.e("Main_screen", "ГУУУУУУУУУУУУУУУУУДДДДДДДДДДДДД");
        } else {

            Log.e("Main_screen", "СМЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭРТЬ");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Drawer_screen.this, Login_screen.class));
            }
        });


    }

}
