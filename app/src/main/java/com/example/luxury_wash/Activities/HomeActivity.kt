package com.example.luxury_wash.Activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.luxury_wash.R
import android.content.Intent

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvAbrirReg = findViewById<TextView>(R.id.tv_registrarse)
        val tvAbrirRecPass = findViewById<TextView>(R.id.tv_recuperarPass)

        tvAbrirReg.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        tvAbrirRecPass.setOnClickListener{
            val intent = Intent(this,PasswordActivity::class.java)
            startActivity(intent)
        }


    }
}