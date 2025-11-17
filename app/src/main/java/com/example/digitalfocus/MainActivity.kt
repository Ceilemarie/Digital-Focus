package com.example.digitalfocus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.digitalfocus.ui.screens.*
import com.example.digitalfocus.ui.theme.DigitalFocusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalFocusTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        // Onboarding
        composable("splash") { SplashScreen(navController = navController) }
        composable("welcome") { WelcomeScreen(navController = navController) }

        composable("login/{userType}") { backStackEntry ->
            val userType = backStackEntry.arguments?.getString("userType") ?: ""
            LoginScreen(navController = navController, userType = userType)
        }

        // Child Flow
        composable("child_character_selection") { CharacterSelectionScreen(navController = navController) }
        composable("growth_level") { GrowthLevelScreen(navController = navController) }
        composable("child_home_dashboard") { ChildHomeDashboard(navController = navController) }




        // Parent Flow
        composable("parent_home_dashboard") { ParentHomeDashboard(navController = navController) }
        composable("parent_statistics") { StatisticsScreen(navController = navController) }
        composable("child_management/{childName}") { backStackEntry ->
            val childName = backStackEntry.arguments?.getString("childName") ?: ""
            ChildManagementScreen(navController = navController, childName = childName)
        }

    }
}
