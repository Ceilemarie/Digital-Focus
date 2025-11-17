package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
fun ChildProfile(navController: NavController) {
    Scaffold(
        containerColor = LightBlueBackground,
        topBar = {
            TopAppBar(
                title = { Text("") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = { CustomBottomNavBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(50.dp)) // Space for the overlapping profile icon

                Card(
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPinkCard),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 70.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Ceile Marie Guce", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkText)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Child", fontSize = 16.sp, color = DarkText)
                            Text(" | ", fontSize = 16.sp, color = DarkText)
                            Text("Grade 6", fontSize = 16.sp, color = DarkText)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Default.People, contentDescription = "Friends", tint = DarkText, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("3", fontSize = 16.sp, color = DarkText)
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        TodayFocusTime()
                        Spacer(modifier = Modifier.height(16.dp))
                        ProgressPerformanceCard()
                        Spacer(modifier = Modifier.height(16.dp))
                        ScreenTimeCard()
                    }
                }
            }

            // Profile picture placeholder overlapping the card
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    Icons.Default.Image,
                    contentDescription = "Profile Picture",
                    tint = Color.Gray,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Composable
fun TodayFocusTime() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Today's Focus Time:", modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.width(120.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Gray,
            )
        )
    }
}

@Composable
fun ProgressPerformanceCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Progress Performance", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ScreenTimeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Screen Time", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFC9E6FF)
@Composable
fun ChildProfilePreview() {
    DigitalFocusTheme {
        ChildProfile(rememberNavController())
    }
}
