package com.example.digitalfocus.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitalfocus.R

// Define the colors from the UI screenshot
val lightBlueBg = Color(0xFFC7E4FF)
val pinkCard = Color(0xFFF9BDBD)
val greenCard = Color(0xFFAFE6B0)
val yellowCard = Color(0xFFF9F0A9)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseInterestScreen(
    onInterestClicked: (String) -> Unit = {},
    onContinueClicked: () -> Unit = {}
) {
    Scaffold(
        containerColor = lightBlueBg,
        topBar = {
            // Top bar with the title "Interest"
            TopAppBar(
                title = { Text("", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            )
        }
    ) { paddingValues ->
        // Main content column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from the Scaffold
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Main title text
            Text(
                text = "Choose what interest you:",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 48.dp)
            )

            // First row of interest cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InterestCard(
                    title = "English",
                    color = pinkCard,
                    iconResId = R.drawable.english_1,
                    onClick = { onInterestClicked("English") }
                )
                InterestCard(
                    title = "Mathematics",
                    color = greenCard,
                    iconResId = R.drawable.math_1,
                    onClick = { onInterestClicked("Mathematics") }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Second row (centered)
            InterestCard(
                title = "Science",
                color = yellowCard,
                iconResId = R.drawable.science_1,
                onClick = { onInterestClicked("Science") }
            )

            // This spacer pushes the button to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // Bottom "Continue" button (no text as per the UI)
            Button(
                onClick = { onContinueClicked() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(30.dp), // Large corner radius
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                // No text, just a white bar
            }

            Spacer(modifier = Modifier.height(48.dp)) // Bottom padding
        }
    }
}

/**
 * A reusable Composable for the interest selection cards.
 */
@Composable
fun InterestCard(
    title: String,
    color: Color,
    @DrawableRes iconResId: Int, // <-- MODIFIED: Changed parameter to accept a Drawable ID
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .size(150.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // <-- MODIFIED: This now uses your custom icon from res/drawable
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, shape = RoundedCornerShape(50)) // White circle bg
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black.copy(alpha = 0.8f)
            )
        }
    }
}

// --- Preview Function ---
// This lets you see the UI directly in Android Studio's "Split" or "Design" view
@Preview(showBackground = true)
@Composable
fun ChooseInterestScreenPreview() {
    ChooseInterestScreen()
}
