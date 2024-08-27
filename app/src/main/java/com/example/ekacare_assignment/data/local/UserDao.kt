package com.example.ekacare_assignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ekacare_assignment.data.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
}