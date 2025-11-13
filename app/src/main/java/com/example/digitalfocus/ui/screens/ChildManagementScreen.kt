package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DigitalFocusTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildManagementScreen(navController: NavController, childName: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(childName) })
        },
        bottomBar = {
            BottomAppBar {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    IconButton(onClick = { navController.navigate("parent_home_dashboard") }) {
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
                .padding(16.dp)
        ) {
            Text("Task List", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            // Placeholder for task list
            TaskItem(taskName = "Wash dishes")
            TaskItem(taskName = "Read book")

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* TODO: Handle View Progress */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Progress")
            }
        }
    }
}

@Composable
fun TaskItem(taskName: String) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = taskName, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun ChildManagementScreenPreview() {
    DigitalFocusTheme {
        ChildManagementScreen(rememberNavController(), "Emman")
    }
}
