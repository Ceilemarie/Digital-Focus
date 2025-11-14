package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DigitalFocusTheme


@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB1D6F6)) // Set background color to B1D6F6
            .padding(16.dp),
        verticalArrangement = Arrangement.Top, // Changed to Arrangement.Top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(174.dp)) // Added Spacer to push content down
        Text(
            text = "WELCOME",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4F2B66) // Set text color to dark purple
        )

        Spacer(modifier = Modifier.height(370.dp)) // Increased spacer to push buttons further down

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8F3EB), contentColor = Color(0xFF4F2B66)),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "LOGIN", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("signup") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8F3EB), contentColor = Color(0xFF4F2B66)),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "SIGNUP", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    DigitalFocusTheme {
        WelcomeScreen(rememberNavController())
    }
}
