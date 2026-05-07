package com.jlls.soundbrain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jlls.soundbrain.ui.components.BottomNavBar
import com.jlls.soundbrain.ui.navigation.AppNavGraph
import com.jlls.soundbrain.ui.navigation.BottomNavItem
import com.jlls.soundbrain.ui.navigation.BottomNavItems
import com.jlls.soundbrain.ui.navigation.Screen
import com.jlls.soundbrain.ui.theme.NexusTheme

/**
 * Main Activity for Nexus Retail Intelligence app.
 * Sets up the main navigation structure with bottom navigation bar.
 *
 * Features:
 * - Edge-to-edge display
 * - Material 3 theme with Instagram-style design
 * - Bottom navigation with 4 tabs (Home, Inventory, Analytics, Profile)
 * - Type-safe navigation using Navigation Compose
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NexusTheme(darkTheme = false) { // Start with light theme
                MainScreen()
            }
        }
    }
}

/**
 * Main screen composable with bottom navigation.
 * Manages the top-level navigation structure.
 */
@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    // Determine if bottom bar should be visible (hide on detail screens)
    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Inventory.route,
        Screen.Analytics.route,
        Screen.Profile.route
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    items = BottomNavItems.items,
                    selectedRoute = currentRoute,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            // Pop up to start destination to avoid building up stack
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            startDestination = Screen.Home.route
        )
    }
}