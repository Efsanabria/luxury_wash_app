package com.example.luxury_wash.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val document: String,
    val names: String,
    val lastName: String,
    val email: String,
    val celphone: String,
    val password: String,
    val status: Int = 1,
    val role: Int = 0
)