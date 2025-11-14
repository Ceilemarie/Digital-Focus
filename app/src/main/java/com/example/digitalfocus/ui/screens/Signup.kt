package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
// NOTE: Removed all Image and painterResource imports
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// NOTE: Removed @DrawableRes import

// --- Custom Colors to Match the UI ---
val FormBackgroundColor = Color(0xFFFEF9F9) // Very light off-white/pink for the card
val InputFieldColor = Color(0xFFFFFFFF)    // White for the input fields
val DividerColor = Color(0xFFE0E0E0)        // Light gray for the divider lines
val LightBlueBackground = Color(0xFFC3E0F7) // Outer background (optional)

@Composable
fun SignupScreen() {
    // State variables for the input fields (used for demonstration)
    val fullName = remember { mutableStateOf("") }
    val emailOrPhone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // Outer background color is light blue (you can wrap this in a Scaffold for full-screen)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueBackground),
        contentAlignment = Alignment.Center
    ) {
        // Main Rounded Card Container
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = FormBackgroundColor),
            modifier = Modifier
                .width(320.dp) // Fixed width for a typical mobile form
                .padding(24.dp)
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Full Name Input Field
                RoundedInputField(
                    value = fullName.value,
                    onValueChange = { fullName.value = it },
                    placeholder = "Enter full name"
                )
                Spacer(modifier = Modifier.height(16.dp))

                // 2. Email or Phone Number Input Field
                RoundedInputField(
                    value = emailOrPhone.value,
                    onValueChange = { emailOrPhone.value = it },
                    placeholder = "Email or Phone Number"
                )
                Spacer(modifier = Modifier.height(16.dp))

                // 3. Password Input Field
                RoundedInputField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    placeholder = "Password",
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(20.dp))

                // 4. Primary Sign-Up/Login Button (Blank, as in the UI)
                Button(
                    onClick = { /* Handle Sign-up Logic */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp)), // Match the rounding of the inputs
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F0F0)), // Light color to keep it empty-looking
                    content = {} // Keep content empty to match the blank rectangle
                )
                Spacer(modifier = Modifier.height(30.dp))

                // 5. 'Sign in with' Divider
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(color = DividerColor, modifier = Modifier.weight(1f), thickness = 1.dp)
                    Text(
                        text = "Sign in with",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Divider(color = DividerColor, modifier = Modifier.weight(1f), thickness = 1.dp)
                }
                Spacer(modifier = Modifier.height(24.dp))

                // 6. Social Media Icons (Now using Placeholders)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Replaced Image buttons with simple boxes
                    SocialIconPlaceholder()
                    Spacer(modifier = Modifier.width(30.dp))
                    SocialIconPlaceholder()
                    Spacer(modifier = Modifier.width(30.dp))
                    SocialIconPlaceholder()
                }
            }
        }
    }
}

// Custom Composable for the Rounded Input Fields (No changes needed)
@Composable
fun RoundedInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = InputFieldColor,
            unfocusedContainerColor = InputFieldColor,
            disabledContainerColor = InputFieldColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(12.dp), // Rounded shape for the pill look
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth()
    )
}

// 🆕 NEW: Custom Composable for the Icon Placeholders
@Composable
fun SocialIconPlaceholder() {
    Box(
        modifier = Modifier
            .size(50.dp) // Size of the box to fit the future icon
            .clip(RoundedCornerShape(12.dp)) // Rounded corners like the original UI
            .background(Color(0xFFFFFFFF)) // White background for the placeholder
            .height(50.dp) // Ensures a square shape
    )
    // You can put a small gray dot or text here if you want to visually confirm it's a placeholder
}

// --- Preview Function ---
@Preview(showBackground = true)
@Composable
fun PreviewSignupScreen() {
    SignupScreen()
}