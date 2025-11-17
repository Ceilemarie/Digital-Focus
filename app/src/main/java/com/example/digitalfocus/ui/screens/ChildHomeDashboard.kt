package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Inventory
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
import com.example.digitalfocus.ui.theme.LeaderboardRowColor
import com.example.digitalfocus.ui.theme.LightBlueBackground
import com.example.digitalfocus.ui.theme.LightGrayText
import com.example.digitalfocus.ui.theme.LightPinkCard

/**
 * Main screen composable that builds the entire UI.
 */
@Composable
fun ChildHomeDashboard(navController: NavController) {
    Scaffold(
        containerColor = LightBlueBackground,
        bottomBar = { CustomBottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopSection()

            Card(
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                colors = CardDefaults.cardColors(containerColor = LightPinkCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(all = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TodayTaskCard()
                    LeaderboardCard()
                    DidYouKnowCard()
                }
            }
        }
    }
}

/**
 * The top part of the screen: "Hi, Ceile!", search/notification icons, etc.
 */
@Composable
fun TopSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.Search, contentDescription = "Search", tint = DarkText)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = DarkText)
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hi, Ceile!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )
    }
}

@Composable
fun TodayTaskCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Today's Task", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = DarkText)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Activity", fontSize = 12.sp, color = LightGrayText)
                    Text("Solving", fontWeight = FontWeight.SemiBold, color = DarkText)
                }
                Column {
                    Text("Time", fontSize = 12.sp, color = LightGrayText)
                    Text("20 mins", fontWeight = FontWeight.SemiBold, color = DarkText)
                }
                Button(
                    onClick = { /* TODO: Start task */ },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightBlueBackground.copy(alpha = 0.8f),
                        contentColor = DarkText
                    )
                ) {
                    Text("Start")
                }
            }
        }
    }
}

@Composable
fun LeaderboardCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Leaderboard", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = DarkText)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AvatarPlaceholder()
                AvatarPlaceholder()
                AvatarPlaceholder()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                LeaderboardRow(rank = 1, name = "Jill Marie")
                LeaderboardRow(rank = 2, name = "Ceile Marie")
            }
        }
    }
}

@Composable
fun LeaderboardRow(rank: Int, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(LeaderboardRowColor)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$rank", fontWeight = FontWeight.Bold, modifier = Modifier.width(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(name, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun AvatarPlaceholder() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}

@Composable
fun DidYouKnowCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Did you know?", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = DarkText)
        }
    }
}

@Composable
fun CustomBottomNavBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightBlueBackground)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Already on Home */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(30.dp), tint = DarkText)
        }
        IconButton(onClick = { /* TODO: Navigate */ }) {
            Icon(Icons.Default.GroupAdd, contentDescription = "Friends", modifier = Modifier.size(30.dp), tint = DarkText)
        }
        IconButton(onClick = { /* TODO: Navigate */ }) {
            Icon(Icons.Outlined.Inventory, contentDescription = "Tasks", modifier = Modifier.size(30.dp), tint = DarkText)
        }
        IconButton(onClick = { /* TODO: Navigate */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings", modifier = Modifier.size(30.dp), tint = DarkText)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFC3E0F7)
@Composable
fun ChildHomeDashboardPreview() {
    DigitalFocusTheme {
        ChildHomeDashboard(rememberNavController())
    }
}
