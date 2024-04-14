package com.example.homefoodapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.homefoodapp.databinding.ActivityRegistrationScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class Registration_screen : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationScreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_screen)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        binding.login.setOnClickListener{
            val intent = Intent(this, Login_screen::class.java)
            startActivity(intent)
        }

        binding.registrationButton.setOnClickListener{
            val name = binding.name.text.toString()
            val email = binding.mail.text.toString()
            val pass = binding.password.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, Login_screen::class.java)

                        val userId: String? = firebaseAuth.currentUser?.uid
                        val documentReference: DocumentReference? = userId?.let {
                            fStore.collection("users").document(userId)
                        }

                        val user = hashMapOf<String, Any>()
                        user.put("Name", name)

                        documentReference?.set(user)?.addOnSuccessListener {
                            Log.d(TAG, "Профиль аккаунт создан для $userId")
                        }

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


//    fun login(v: View) {
//        val intent = Intent(this, Login_screen::class.java)
//        startActivity(intent)
//    }
//
//    fun registration(v: View) {
//        val intent = Intent(this, Main_screen::class.java)
//        startActivity(intent)
//    }
}