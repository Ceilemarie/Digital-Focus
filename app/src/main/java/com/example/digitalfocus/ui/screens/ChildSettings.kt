package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DarkText
import com.example.digitalfocus.ui.theme.DigitalFocusTheme
import com.example.digitalfocus.ui.theme.LightBlueBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildSettings(navController: NavController) {
    Scaffold(
        containerColor = LightBlueBackground,
        bottomBar = { CustomBottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Row for Notifications and Profile Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End, // Align items to the right
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO: Notifications action */ }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = DarkText)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(40.dp) // Profile pic size
                        .clip(CircleShape)
                        .background(Color.LightGray) // Placeholder for profile picture
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Large empty card
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                // Content of the card goes here
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChildSettingsPreview() {
    DigitalFocusTheme {
        ChildSettings(rememberNavController())
    }
}
