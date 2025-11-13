package com.example.digitalfocus.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BlockingView(onExitClick: () -> Unit) {
    var isRadioButtonSelected by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Time to take a break!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(
                    selected = isRadioButtonSelected,
                    onClick = { isRadioButtonSelected = true }
                )
                Text(text = "I acknowledge I want to continue")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onExitClick, enabled = isRadioButtonSelected) {
                Text("Continue")
            }
        }
    }
}