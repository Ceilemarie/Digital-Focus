package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DigitalFocusTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGardenScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Garden") })
        },
        bottomBar = {
            BottomAppBar {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    IconButton(onClick = { navController.navigate("child_home_dashboard") }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { /* Already on Garden screen */ }) {
                        Icon(Icons.Filled.List, contentDescription = "Garden") // Placeholder
                    }
                    IconButton(onClick = { /* TODO: Navigate to Shop */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Shop")
                    }
                    IconButton(onClick = { /* TODO: Navigate to Profile */ }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder for the garden visualization
            Text("Garden with several plants", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyGardenScreenPreview() {
    DigitalFocusTheme {
        MyGardenScreen(rememberNavController())
    }
}
