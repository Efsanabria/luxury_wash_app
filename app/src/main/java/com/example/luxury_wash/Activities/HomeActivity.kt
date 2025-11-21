package com.example.luxury_wash.Activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.luxury_wash.R
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.example.luxury_wash.data.AppDatabase
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val etEmail = findViewById<EditText>(R.id.edt_email_home)
        val etPassword = findViewById<EditText>(R.id.edt_password_home)
        val btnLogin = findViewById<Button>(R.id.btn_inicio_sesion)

        val db = AppDatabase.getInstance(this)
        val userDao = db.userDao()

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

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = userDao.getUserByEmail(email)

                if (user == null) {
                    runOnUiThread {
                        Toast.makeText(
                            this@HomeActivity,
                            "Usuario no encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@launch
                }

                if (user.password != password) {
                    runOnUiThread {
                        Toast.makeText(
                            this@HomeActivity,
                            "Contraseña incorrecta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@launch
                }

                if (user.status == 0) {
                    runOnUiThread {
                        Toast.makeText(
                            this@HomeActivity,
                            "Tu usuario está inactivo",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    return@launch
                }

                // Si todo está OK → pasar a ServicesActivity
                runOnUiThread {

                    Toast.makeText(
                        this@HomeActivity,
                        "Bienvenido ${user.names}",
                        Toast.LENGTH_SHORT
                    ).show()

                    val isAdmin = user.role == 1

                    if (isAdmin) {
                        val intent = Intent(this@HomeActivity, AdminActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@HomeActivity, ServicesActivity::class.java)
                        startActivity(intent)
                    }

                    finish()
                }
            }
        }
    }
}