package com.example.luxury_wash.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.luxury_wash.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users ORDER BY names ASC")
    suspend fun getAllUsers(): List<User>

    @Query("UPDATE users SET status = :newStatus WHERE id = :userId")
    suspend fun updateStatus(userId: Int, newStatus: Int)

    @Query("UPDATE users SET role = :newRole WHERE id = :userId")
    suspend fun updateRole(userId: Int, newRole: Int)
}