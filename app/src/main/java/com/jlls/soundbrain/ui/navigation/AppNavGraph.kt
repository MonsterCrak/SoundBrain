package com.jlls.soundbrain.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jlls.soundbrain.ui.screens.detail.ProductDetailScreen
import com.jlls.soundbrain.ui.screens.home.HomeScreen
import com.jlls.soundbrain.ui.screens.inventory.InventoryScreen

/**
 * Navigation graph for the app.
 * Type-safe navigation using NavHostController.
 *
 * @param navController The navigation controller
 * @param startDestination Starting destination route
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Home Screen
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        // Inventory Screen
        composable(route = Screen.Inventory.route) {
            InventoryScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        // Analytics Screen (placeholder)
        composable(route = Screen.Analytics.route) {
            PlaceholderScreen(title = "Analytics", description = "Coming soon")
        }

        // Profile Screen (placeholder)
        composable(route = Screen.Profile.route) {
            PlaceholderScreen(title = "Profile", description = "Coming soon")
        }

        // Product Detail Screen
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(
                productId = productId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

/**
 * Placeholder screen composable for screens under development.
 */
@Composable
private fun PlaceholderScreen(
    title: String,
    description: String
) {
    androidx.compose.foundation.layout.Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        androidx.compose.material3.Text(
            text = title,
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
        androidx.compose.foundation.layout.Spacer(
            modifier = androidx.compose.ui.Modifier.height(8.dp)
        )
        androidx.compose.material3.Text(
            text = description,
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun androidx.compose.ui.Modifier.fillMaxSize() =
    this.then(androidx.compose.foundation.layout.fillMaxSize())

private fun androidx.compose.ui.Modifier.height(dp: Int) =
    this.then(androidx.compose.foundation.layout.height(dp.dp))

private fun androidx.compose.foundation.layout.Spacer(modifier: androidx.compose.ui.Modifier) =
    androidx.compose.foundation.layout.Spacer(modifier = modifier)