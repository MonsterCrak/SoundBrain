package com.jlls.soundbrain.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Navigation routes for SoundBrain Music AI.
 */
sealed class Screen(val route: String) {
    data object Inicio : Screen("inicio")
    data object Generador : Screen("generador")
    data object Perfil : Screen("perfil")
}

/**
 * Bottom navigation items for SoundBrain.
 */
object BottomNavItems {
    val items = listOf(
        BottomNavItem(
            route = Screen.Inicio.route,
            label = "Library",
            icon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ),
        BottomNavItem(
            route = Screen.Generador.route,
            label = "Create",
            icon = Icons.Outlined.MusicNote,
            selectedIcon = Icons.Filled.MusicNote
        ),
        BottomNavItem(
            route = Screen.Perfil.route,
            label = "Profile",
            icon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person
        )
    )
}

/**
 * Data class representing a bottom navigation item.
 */
data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
)
