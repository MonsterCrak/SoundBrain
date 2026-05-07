package com.jlls.soundbrain.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlls.soundbrain.ui.theme.Divider

@Preview(showBackground = true)
@Composable
fun ShimmerEffectPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        ShimmerEffect(widthFactor = 0.7f, height = 20)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardShimmerPreview() {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        ProductCardShimmer(modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryShimmerListPreview() {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        InventoryShimmerList()
    }
}

/**
 * Shimmer effect composable for loading states.
 * Creates an animated gradient that simulates content loading.
 *
 * Based on mobile performance best practices - lightweight animation
 * using only opacity and transform concepts.
 *
 * @param modifier Modifier for the shimmer container
 * @param widthFactor Width of the shimmer highlight (0-1)
 * @param height Height of the shimmer element
 */
@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
    widthFactor: Float = 0.3f,
    height: Int = 16
) {
    val shimmerColors = listOf(
        Divider,
        Color.White,
        Divider
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim - 500f, 0f),
        end = Offset(translateAnim, 0f)
    )

    Box(
        modifier = modifier
            .fillMaxWidth(widthFactor)
            .height(height.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(brush)
    )
}

/**
 * Shimmer placeholder for product card loading state.
 * Shows a skeleton representation of the product card.
 *
 * @param modifier Modifier for the placeholder container
 */
@Composable
fun ProductCardShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Image placeholder
        ShimmerEffect(
            height = 180,
            widthFactor = 1f
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title placeholder
        ShimmerEffect(
            widthFactor = 0.7f,
            height = 20
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description placeholder
        ShimmerEffect(
            widthFactor = 0.9f,
            height = 14
        )
        ShimmerEffect(
            widthFactor = 0.6f,
            height = 14
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Price and status row
        ShimmerEffect(
            widthFactor = 0.3f,
            height = 16
        )
    }
}

/**
 * Full screen shimmer loading state.
 * Used when the entire inventory list is loading.
 *
 * @param modifier Modifier for the container
 */
@Composable
fun InventoryShimmerList(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        repeat(5) {
            ProductCardShimmer(
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}