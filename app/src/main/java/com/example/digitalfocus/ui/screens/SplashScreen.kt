package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Simulate a delay and then navigate to the welcome screen
    LaunchedEffect(key1 = true) {
        delay(2000) // 2-second delay
        navController.navigate("welcome") {
            // Pop up to the start destination of the graph to
            // avoid building up a large back stack as the user navigates
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Splash Screen", style = MaterialTheme.typography.headlineLarge)
        // TODO: Add the brain logo from the design
    }
}
