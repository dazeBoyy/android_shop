package com.example.homefoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Loading_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)

        supportActionBar?.hide()

        Handler().postDelayed(
            {
                val intent = Intent(this, Welcome_screen::class.java)
                startActivity(intent)
                finish()
            }, 500
        )
    }
}