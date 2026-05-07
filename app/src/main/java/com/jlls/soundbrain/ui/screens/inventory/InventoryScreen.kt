package com.jlls.soundbrain.ui.screens.inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlls.soundbrain.ui.components.InventoryShimmerList
import com.jlls.soundbrain.ui.components.ProductCard
import com.jlls.soundbrain.ui.theme.Background
import com.jlls.soundbrain.ui.theme.SoundBrainTheme

/**
 * Inventory screen displaying products in an Instagram-style feed.
 * Features:
 * - LazyColumn with Instagram-post-like product cards
 * - Loading shimmer effect
 * - Clean white cards on soft background
 *
 * @param viewModel InventoryViewModel instance
 * @param onProductClick Callback when a product is clicked
 * @param modifier Modifier for the screen
 */
@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel = viewModel(),
    onProductClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Screen Title
        Text(
            text = "Inventory",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        // Loading state - show shimmer
        if (uiState.isLoading) {
            InventoryShimmerList()
        } else {
            // Product List
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = uiState.products,
                    key = { product -> product.id }
                ) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) }
                    )
                }

                // Bottom padding for nav bar
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun InventoryScreenPreview() {
    SoundBrainTheme {
        InventoryScreen()
    }
}
