package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DarkText
import com.example.digitalfocus.ui.theme.DigitalFocusTheme
import com.example.digitalfocus.ui.theme.LightBlueBackground
import com.example.digitalfocus.ui.theme.LightPinkCard // Reusing LightPinkCard for button background

/**
 * Screen where the user selects their role (Child or Parent).
 */
@Composable
fun SelectRoleScreen(navController: NavController) {
    // Scaffold provides the basic structure (like the background color)
    Scaffold(
        containerColor = LightBlueBackground,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 40.dp), // Add padding on the sides
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 1. Title Text
            Text(
                text = "Select your Role:",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(bottom = 60.dp)
            )

            // 2. Child Button
            RoleButton(text = "Child") {
                // TODO: Handle navigation to the Child's dashboard/login screen
                // navController.navigate("child_home")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Parent Button
            RoleButton(text = "Parent") {
                // TODO: Handle navigation to the Parent's dashboard/login screen
                // navController.navigate("parent_home")
            }
        }
    }
}

/**
 * Reusable composable for the large role selection buttons.
 */
@Composable
fun RoleButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(16.dp), // Rounded corners matching the design
        colors = ButtonDefaults.buttonColors(
            containerColor = LightPinkCard, // Using a light color for the background
            contentColor = DarkText
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// --- Preview ---

@Preview(showBackground = true, name = "Select Role Screen")
@Composable
fun SelectRoleScreenPreview() {
    DigitalFocusTheme {
        SelectRoleScreen(rememberNavController())
    }
}