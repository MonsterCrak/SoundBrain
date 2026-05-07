package com.jlls.soundbrain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jlls.soundbrain.ui.components.GlassBottomNavBar
import com.jlls.soundbrain.ui.navigation.AppNavGraph
import com.jlls.soundbrain.ui.navigation.BottomNavItems
import com.jlls.soundbrain.ui.navigation.Screen
import com.jlls.soundbrain.ui.theme.SoundBrainTheme

/**
 * Main Activity for SoundBrain Music AI app.
 * Sets up the main navigation structure with glassmorphism floating bottom nav.
 *
 * Features:
 * - Edge-to-edge display
 * - Material 3 theme with zen-minimalism design
 * - Floating bottom navigation with 3 tabs (Inicio, Generador, Perfil)
 * - Type-safe navigation using Navigation Compose
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SoundBrainTheme {
                MainScreen()
            }
        }
    }
}

/**
 * Main screen composable with glassmorphism floating bottom navigation.
 * Manages the top-level navigation structure.
 */
@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Inicio.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            GlassBottomNavBar(
                items = BottomNavItems.items,
                selectedRoute = currentRoute,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(Screen.Inicio.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            startDestination = Screen.Inicio.route
        )
    }
}
