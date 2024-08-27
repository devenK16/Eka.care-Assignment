package com.example.ekacare_assignment.domain.repository

import com.example.ekacare_assignment.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun getAllUsers(): Flow<List<User>>
}