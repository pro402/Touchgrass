package com.example.viewbiendingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.viewbiendingapp.databinding.ActivityTreeBinding

class tree : AppCompatActivity() {
    private lateinit var b: ActivityTreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityTreeBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.t.setOnClickListener{
            Toast.makeText(this,"Go Out and touch some Grass Take a Break!!!!",Toast.LENGTH_SHORT).show()
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        // Exit the app directly from the sign-in page
        finishAffinity()
    }
}