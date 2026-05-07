package com.jlls.soundbrain.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jlls.soundbrain.ui.navigation.BottomNavItem

/**
 * Glassmorphism floating bottom navigation bar.
 * Features:
 * - Centered with horizontal padding (not edge-to-edge)
 * - Blur effect with semi-transparent white background
 * - Thin-line icons (Outlined variants), NO text labels
 * - Active state: tiny 4dp dot indicator above icon
 * - AnimatedVisibility for dot indicator
 */
@Composable
fun GlassBottomNavBar(
    items: List<BottomNavItem>,
    selectedRoute: String,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 16.dp)
    ) {
        // Blur layer behind
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(32.dp))
                .blur(20.dp)
                .background(Color.White.copy(alpha = 0.85f))
        )

        // Main glass container
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White.copy(alpha = 0.8f))
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val selected = item.route == selectedRoute
                GlassNavItem(
                    item = item,
                    selected = selected,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
private fun GlassNavItem(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        // Animated dot indicator
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn() + slideIn { androidx.compose.ui.unit.IntOffset(0, 8) },
            exit = fadeOut() + slideOut { androidx.compose.ui.unit.IntOffset(0, 8) }
        ) {
            Box(
                modifier = Modifier
                    .size(4.dp, 4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Icon - using outlined variant for thin-line look
        Icon(
            imageVector = if (selected) item.selectedIcon else item.icon,
            contentDescription = item.label,
            modifier = Modifier.size(24.dp),
            tint = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}
