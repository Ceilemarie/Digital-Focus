package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
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
fun ParentHomeDashboard(navController: NavController) {
    val children = listOf("Emman", "Carl") // Placeholder data

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
                    IconButton(onClick = { navController.navigate("parent_statistics") }) {
                        Icon(Icons.Filled.List, contentDescription = "Stats") // Placeholder
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
            // Placeholder for parent profile
            Text("Parent Profile Section", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(32.dp))

            // List of managed children
            Text("Managed Children", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                children.forEach { childName ->
                    Text(
                        text = childName,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("child_management/$childName") }
                            .padding(vertical = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Placeholder for TASKS section
            Text("TASKS", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParentHomeDashboardPreview() {
    DigitalFocusTheme {
        ParentHomeDashboard(rememberNavController())
    }
}
