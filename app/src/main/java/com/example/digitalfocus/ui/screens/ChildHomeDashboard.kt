package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DigitalFocusTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildHomeDashboard(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("HOME") })
        },
        bottomBar = {
            BottomAppBar {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    IconButton(onClick = { /* Already on Home screen */ }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { navController.navigate("child_garden") }) {
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top section with avatar and stats
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(64.dp)) { /* Placeholder for Avatar */ }
                Spacer(modifier = Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Points: 100")
                    Text("Streak: 5 days")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Middle: List of tasks
            Text("Tasks", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            // Placeholder for the task list
            Column {
                Text("- Plant trees")
                Text("- Read books")
                Text("- Feed pet")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChildHomeDashboardPreview() {
    DigitalFocusTheme {
        ChildHomeDashboard(rememberNavController())
    }
}
