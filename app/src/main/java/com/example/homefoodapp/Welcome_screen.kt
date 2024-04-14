package com.example.homefoodapp

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Welcome_screen : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        firebaseAuth = FirebaseAuth.getInstance()

    }

    fun registration(v: View) {
        val intent = Intent(this, Registration_screen::class.java)
        startActivity(intent)
    }

    fun login(v: View) {
        val intent = Intent(this, Login_screen::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser != null){
            val intent = Intent(this,Main_screen::class.java)
            startActivity(intent)
        }
    }






}