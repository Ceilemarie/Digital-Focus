package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DigitalFocusTheme
import kotlinx.coroutines.delay

@Composable
fun GrowthLevelScreen(navController: NavController) {
    // This is an intermediary screen, so we'll show a message and then navigate
    // to the child's home dashboard after a short delay.
    LaunchedEffect(key1 = true) {
        delay(2000) // 2-second delay
        navController.navigate("child_home_dashboard") {
            // Clear the back stack up to this screen
            popUpTo("growth_level") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Growth Level", style = MaterialTheme.typography.headlineLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun GrowthLevelScreenPreview() {
    DigitalFocusTheme {
        GrowthLevelScreen(rememberNavController())
    }
}
