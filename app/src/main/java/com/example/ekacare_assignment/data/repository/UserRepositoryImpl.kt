package com.example.ekacare_assignment.data.repository

import com.example.ekacare_assignment.data.local.UserDao
import com.example.ekacare_assignment.data.model.User
import com.example.ekacare_assignment.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }
}