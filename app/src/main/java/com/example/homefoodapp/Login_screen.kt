package com.example.homefoodapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homefoodapp.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class Login_screen : AppCompatActivity() {

    private lateinit var binding:ActivityLoginScreenBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.registration.setOnClickListener{
            val intent = Intent(this, Registration_screen::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener{
            val email = binding.mail.text.toString()
            val pass = binding.password.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, Information_screen::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Пустые поля запрещены", Toast.LENGTH_SHORT).show()
            }
        }


    }
}