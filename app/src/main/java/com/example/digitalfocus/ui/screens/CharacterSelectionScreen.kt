package com.example.digitalfocus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.theme.DigitalFocusTheme

@Composable
fun CharacterSelectionScreen(navController: NavController) {
    val characters = (1..6).toList()
    var selectedCharacter by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose your character",
            fontSize = 28.sp,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(characters) { character ->
                val isSelected = selectedCharacter == character
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray)
                        .clickable { selectedCharacter = character },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Avatar $character", color = if (isSelected) Color.White else Color.Black)
                }
            }
        }

        Button(
            onClick = {
                // Navigate to the next screen in the flow
                 navController.navigate("growth_level")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            enabled = selectedCharacter != null
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterSelectionScreenPreview() {
    DigitalFocusTheme {
        CharacterSelectionScreen(rememberNavController())
    }
}
