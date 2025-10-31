package com.example.luxury_wash.Activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.luxury_wash.R
import android.content.Intent

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvAbrirHometwo = findViewById<TextView>(R.id.tv_homeRegistertwo)

        tvAbrirHometwo.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}