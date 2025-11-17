package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Inventory
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DarkText
import com.example.digitalfocus.ui.theme.DigitalFocusTheme
import com.example.digitalfocus.ui.theme.LightBlueBackground
import com.example.digitalfocus.ui.theme.LightPinkCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentsProfile(navController: NavController) {
    Scaffold(
        containerColor = LightBlueBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Handle FAB click */ },
                shape = CircleShape,
                containerColor = Color.White,
                contentColor = DarkText
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            ParentBottomAppBar(navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp)) // Space for the overlapping profile icon

                ParentsProfileCard()
            }

            // Parent's profile picture, overlapping the card
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {}
        }
    }
}

@Composable
fun ParentsProfileCard() {
    Card(
        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(containerColor = LightPinkCard),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, bottom = 32.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Lana Del Rey", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkText)
            Text("Parent", fontSize = 16.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(24.dp))

            // My Children Section
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("My Children", fontWeight = FontWeight.Bold, color = DarkText)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ChildItem(name = "Ceile")
                    Spacer(modifier = Modifier.width(12.dp))
                    ChildItem(name = "Jill")
                    Spacer(modifier = Modifier.width(12.dp))
                    ChildItem(name = "Carl")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons
            ActionButton(text = "Assign Task")
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Manage Buddies")
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "", enabled = false) // Empty button
        }
    }
}

@Composable
fun ChildItem(name: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.size(width = 80.dp, height = 100.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {}
            Spacer(modifier = Modifier.height(8.dp))
            Text(name, fontSize = 14.sp, color = DarkText, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ActionButton(text: String, enabled: Boolean = true) {
    Button(
        onClick = { /* TODO */ },
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        enabled = enabled
    ) {
        if (text.isNotEmpty()) {
            Text(text, color = DarkText, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun ParentBottomAppBar(navController: NavController) {
    BottomAppBar(
        containerColor = LightBlueBackground,
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        IconButton(onClick = { /* TODO: Navigate */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home", tint = DarkText)
        }
        IconButton(onClick = { /* TODO: Navigate */ }) {
            Icon(Icons.Default.Group, contentDescription = "Buddies", tint = DarkText)
        }
        Spacer(Modifier.weight(1f, true)) // Spacer for the FAB
        IconButton(onClick = { /* TODO: Navigate */ }) {
            Icon(Icons.Outlined.Inventory, contentDescription = "Tasks", tint = DarkText)
        }
        IconButton(onClick = { /* TODO: Navigate */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = DarkText)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFC9E6FF)
@Composable
fun ParentsProfilePreview() {
    DigitalFocusTheme {
        ParentsProfile(rememberNavController())
    }
}
