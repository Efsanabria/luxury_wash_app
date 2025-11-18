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
import com.example.luxury_wash.models.User
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etDocument = findViewById<EditText>(R.id.et_document)
        val etNames = findViewById<EditText>(R.id.et_names)
        val etLastName = findViewById<EditText>(R.id.et_lastName)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etCelphone = findViewById<EditText>(R.id.et_celphone)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val etConfirmPass = findViewById<EditText>(R.id.et_confirmPass)
        val btnSave = findViewById<Button>(R.id.btn_guardarRegister)
        val tvAbrirHometwo = findViewById<TextView>(R.id.tv_homeRegistertwo)

        val db = AppDatabase.getInstance(this)
        val userDao = db.userDao()

        tvAbrirHometwo.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        btnSave.setOnClickListener {
            val document = etDocument.text.toString().trim()
            val names = etNames.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val celphone = etCelphone.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPass = etConfirmPass.text.toString().trim()

            if (document.isEmpty() || names.isEmpty() || lastName.isEmpty() ||
                email.isEmpty() || celphone.isEmpty() ||
                password.isEmpty() || confirmPass.isEmpty()
            ) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPass) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val existingUser = userDao.getUserByEmail(email)

                if (existingUser != null) {
                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Ya existe un usuario con ese correo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    val user = User(
                        document = document,
                        names = names,
                        lastName = lastName,
                        email = email,
                        celphone = celphone,
                        password = password,
                        status = 1 // ACTIVO por defecto
                    )

                    userDao.insertUser(user)

                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Usuario registrado correctamente",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}