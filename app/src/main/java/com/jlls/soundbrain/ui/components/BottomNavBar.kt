package com.jlls.soundbrain.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jlls.soundbrain.ui.theme.OnSurface
import com.jlls.soundbrain.ui.theme.Primary
import com.jlls.soundbrain.ui.theme.Surface

/**
 * Navigation item data class for type-safe navigation.
 */
data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
)

/**
 * Custom bottom navigation bar with Instagram-style minimal design.
 * Features:
 * - 4 navigation items (Home, Inventory, Analytics, Profile)
 * - Clean iconography
 * - Smooth selected state transitions
 * - Primary color for selected item
 *
 * @param items List of navigation items
 * @param selectedRoute Currently selected route
 * @param onItemClick Callback when an item is clicked
 * @param modifier Modifier for the navigation bar
 */
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    selectedRoute: String,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Surface,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            val selected = item.route == selectedRoute

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    selectedTextColor = Primary,
                    unselectedIconColor = OnSurface.copy(alpha = 0.6f),
                    unselectedTextColor = OnSurface.copy(alpha = 0.6f),
                    indicatorColor = Primary.copy(alpha = 0.1f)
                )
            )
        }
    }
}