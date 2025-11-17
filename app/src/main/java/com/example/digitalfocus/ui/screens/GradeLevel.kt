package com.example.digitalfocus.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

// Ensure these non-M3 imports are also present:
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
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
import com.example.digitalfocus.ui.theme.LightPinkCard

// Define the list of grade levels
val gradeLevels = (4..12).map { "Grade $it" }

/**
 * Screen where the user selects their academic grade level using an Exposed Dropdown Menu.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeLevelScreen(navController: NavController) {
    Scaffold(
        containerColor = LightBlueBackground,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        // Use a Box to layer the main content (Column) and the fixed button (Button)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 1. Main Content - Centered in the middle
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Title Text
                Text(
                    text = "Grade Level:",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DarkText,
                    modifier = Modifier.padding(bottom = 60.dp)
                )

                // Exposed Dropdown Menu for Grade Selection
                GradeLevelDropdown()
            }

            // 2. Continue Button - Fixed at the bottom
            ContinueButton(
                onClick = {
                    // TODO: Implement navigation to the next screen (e.g., child dashboard)
                    // navController.navigate("child_home")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter) // Stick to the bottom
                    .padding(horizontal = 40.dp, vertical = 32.dp) // Add spacing
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeLevelDropdown() {
    // State to track if the dropdown menu is expanded or collapsed
    var isExpanded by remember { mutableStateOf(false) }

    // State to hold the currently selected grade level
    var selectedGrade by remember { mutableStateOf("What is your Grade Level?") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = Modifier.width(300.dp)
    ) {
        // The visible input field (TextField)
        TextField(
            value = selectedGrade,
            onValueChange = { /* Do nothing */ },
            readOnly = true,
            label = { Text("What is your Grade Level?") }, // Added a Label to match the design better
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier.menuAnchor()
        )

        // The dropdown menu that appears when the TextField is clicked
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            gradeLevels.forEach { grade ->
                DropdownMenuItem(
                    text = { Text(grade) },
                    onClick = {
                        selectedGrade = grade
                        isExpanded = false
                        // TODO: Save the grade selection state
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Composable
fun ContinueButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkText, // Use a contrasting color (DarkText) for visibility
            contentColor = LightPinkCard // Use a light color for text
        )
    ) {
        Text(
            text = "Next",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// --- Preview ---

@Preview(showBackground = true, name = "Grade Level Screen")
@Composable
fun GradeLevelScreenPreview() {
    // DigitalFocusTheme { //
    GradeLevelScreen(rememberNavController())
    // }
}