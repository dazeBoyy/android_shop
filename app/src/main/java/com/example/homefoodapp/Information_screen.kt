package com.example.homefoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Information_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_screen)
    }

    fun adress_page(v: View) {
        val intent = Intent(this, Adress_screen::class.java)
        startActivity(intent)
    }
}