package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriends(navController: NavController) {
    Scaffold(
        containerColor = LightBlueBackground,
        bottomBar = { CustomBottomNavBar(navController) }
    ) { innerPadding ->
        // The main content of the screen
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp), // Horizontal padding for the whole content
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Row for Search, Notifications, and Profile Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End, // Align items to the right
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO: Search action */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = DarkText)
                }
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

            Spacer(modifier = Modifier.height(24.dp)) // Space between icons and "Study Buddies"

            Text(
                text = "Study Buddies",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth(), // The line should span the width
                color = DarkText,
                thickness = 1.dp
            )

            ConnectSection()

            val studyBuddies = listOf(
                StudyBuddy("Ceile Marie Guce", "Grade 12 | NU Lipa", true),
                StudyBuddy("Jill Marie Lomeda", "Grade 12 | NU Lipa", false),
                StudyBuddy("Carl Laurence Salazar", "Grade 8 | Fernando Air Base High School", false),
                StudyBuddy("Emmanuelle Pranada", "Grade 8 | Fernando Air Base High School", false)
            )

            LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
                items(studyBuddies) { buddy ->
                    StudyBuddyItem(buddy = buddy)
                }
            }
        }
    }
}


@Composable
fun ConnectSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 8.dp)
    ) {
        Divider(modifier = Modifier.weight(1f), color = DarkText)
        Text(
            text = "Connect",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = DarkText,
            fontSize = 12.sp
        )
        Divider(modifier = Modifier.weight(1f), color = DarkText)
    }
}

data class StudyBuddy(val name: String, val details: String, val isFriend: Boolean)

@Composable
fun StudyBuddyItem(buddy: StudyBuddy) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .height(64.dp), // Match image height
            verticalArrangement = Arrangement.Center // Center text vertically
        ) {
            Text(text = buddy.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkText)
            Text(text = buddy.details, fontSize = 14.sp, color = Color.Gray)
        }

        if (buddy.isFriend) {
            Row {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.height(36.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Text("Add", color = DarkText, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    modifier = Modifier.height(36.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp)
                ) {
                    Text("Remove", color = DarkText, fontSize = 12.sp)
                }
            }
        } else {
            Row {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .height(36.dp)
                        .width(60.dp)
                ) { }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    modifier = Modifier
                        .height(36.dp)
                        .width(60.dp)
                ) { }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddFriendsPreview() {
    DigitalFocusTheme {
        AddFriends(rememberNavController())
    }
}
