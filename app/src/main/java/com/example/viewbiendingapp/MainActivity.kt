package com.example.viewbiendingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.viewbiendingapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tSIn.setOnClickListener {
            val i = Intent(this,signin::class.java)
            startActivity(i)
        }
        binding.enter.setOnClickListener{
            val n = binding.name.text.toString()
            val e = binding.email.text.toString()
            val p = binding.pass.text.toString()
            val id = binding.ID.text.toString()
            val u = User(n,id,e,p)
            if(n.isNotBlank() && id.isNotBlank() && e.isNotBlank() && p.isNotBlank()){
                db = FirebaseDatabase.getInstance().getReference("Users")
                db.child(id).setValue(u).addOnSuccessListener {
                    Toast.makeText(this,"SuccessFully Registered",Toast.LENGTH_SHORT).show()
                    val i = Intent(this,tree::class.java)
                    startActivity(i)
                }.addOnFailureListener{
                    Toast.makeText(this,"Failed to Connect DataBase",Toast.LENGTH_SHORT).show()
                }

                binding.name.setText("")
                binding.email.setText("")
                binding.pass.setText("")
                binding.ID.setText("")


            }else{
                Toast.makeText(this,"Please Enter Name,Email,Password",Toast.LENGTH_SHORT).show()
            }
        }
    }
}