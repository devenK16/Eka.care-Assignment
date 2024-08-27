package com.example.ekacare_assignment.presentation

import UserFormScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ekacare_assignment.ui.theme.EkacareAssignmentTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EkacareAssignmentTheme {
                StatusBarColor()
                val viewModel: UserViewModel = hiltViewModel()
                UserFormScreen(viewModel)

            }
        }
    }

    @Composable
    private fun StatusBarColor() {
        val systemUiController = rememberSystemUiController()
        val color = Color.White
        LaunchedEffect(color) {
            systemUiController.setSystemBarsColor(color)
        }
    }
}

