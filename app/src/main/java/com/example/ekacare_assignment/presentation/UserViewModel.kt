package com.example.ekacare_assignment.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ekacare_assignment.data.model.User
import com.example.ekacare_assignment.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _validationError = MutableStateFlow<String?>(null)
    val validationError: StateFlow<String?> = _validationError

    private val _userCount = MutableStateFlow(0)
    val userCount: StateFlow<Int> = _userCount

    init {
        viewModelScope.launch {
            repository.getAllUsers().collect { userList ->
                _users.value = userList
                _userCount.value = userList.size
            }
        }
    }

    fun validateAndInsertUser(name: String, age: String, dob: String, address: String): Boolean {
        if (name.trim().isBlank()) {
            _validationError.value = "*Name cannot be empty"
            return false
        }
        if (age.isBlank() || age.toIntOrNull() == null) {
            _validationError.value = "*Age cannot be empty"
            return false
        }
        if (dob.isBlank()) {
            _validationError.value = "*Date of Birth cannot be empty, Select your date of birth"
            return false
        }
        if (address.trim().isBlank()) {
            _validationError.value = "*Address cannot be empty"
            return false
        }

        _validationError.value = null
        insertUser(name, age.toInt(), dob, address)
        logAllUsers()
        return true
    }

    fun insertUser(name: String, age: Int, dob: String, address: String) {
        viewModelScope.launch {
            val user = User(name = name.trim(), age = age, dob = dob, address = address.trim())
            repository.insertUser(user)
        }
    }

    private fun logAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers().collect { userList ->
                userList.forEach { user ->
                    Log.d("UserViewModel", "User: $user")
                }
            }
        }
    }
}