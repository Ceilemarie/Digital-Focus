package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder for the logo
        Box(
            modifier = Modifier
                .size(150.dp)
        ) {
            // In a real app, you would use an Image composable here
            Text(text = "Logo", modifier = Modifier.align(Alignment.Center))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "WELCOME",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = { navController.navigate("login/Parent") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Parent", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("login/Child") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Child", fontSize = 18.sp)
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
