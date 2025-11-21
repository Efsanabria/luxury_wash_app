package com.example.luxury_wash.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luxury_wash.R
import com.example.luxury_wash.adapters.AdminAdapter
import com.example.luxury_wash.data.AppDatabase
import com.example.luxury_wash.models.User
import kotlinx.coroutines.launch

class AdminActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: AdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        rv = findViewById(R.id.rv_admin_users)
        rv.layoutManager = LinearLayoutManager(this)

        adapter = AdminAdapter(
            onStatusToggle = { user -> toggleStatus(user) },
            onRoleToggle = { user -> toggleRole(user) }
        )
        rv.adapter = adapter

        loadUsers()
    }

    private fun loadUsers() {
        lifecycleScope.launch {
            val db = AppDatabase.getInstance(this@AdminActivity)
            val users = db.userDao().getAllUsers()

            runOnUiThread {
                adapter.submit(users)
            }
        }
    }

    private fun toggleStatus(user: User) {
        lifecycleScope.launch {
            val db = AppDatabase.getInstance(this@AdminActivity)
            val newStatus = if (user.status == 1) 0 else 1
            db.userDao().updateStatus(user.id, newStatus)
            loadUsers()
        }
    }

    private fun toggleRole(user: User) {
        lifecycleScope.launch {
            val db = AppDatabase.getInstance(this@AdminActivity)
            val newRole = if (user.role == 1) 0 else 1
            db.userDao().updateRole(user.id, newRole)
            loadUsers()
        }
    }
}