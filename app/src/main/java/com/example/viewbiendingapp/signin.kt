package com.example.viewbiendingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.viewbiendingapp.databinding.ActivityMainBinding
import com.example.viewbiendingapp.databinding.ActivitySigninBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {
    private lateinit var binding : ActivitySigninBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tSUp.setOnClickListener {
            val i =Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        database = FirebaseDatabase.getInstance().getReference("Users")

            binding.enter.setOnClickListener {
            val p = binding.pass.text.toString()
            val usd = binding.ID.text.toString()

            if (usd.isNotBlank()) {
                database.child(usd).get().addOnSuccessListener { dataSnapshot ->
                    if (dataSnapshot.exists()) {
                        val passwd = dataSnapshot.child("password").value

                        if (passwd.toString() == p) {
                            intent = Intent(this, tree::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Wrong Password", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this, "Try Sign Up first", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "FAILED TO CONNECT DB", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please Enter User Unique Id", Toast.LENGTH_SHORT).show()
            }
        }
    }
}