package com.jlls.soundbrain.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlls.soundbrain.ui.components.ActivityItem
import com.jlls.soundbrain.ui.components.StatCard
import com.jlls.soundbrain.ui.components.StatsRow
import com.jlls.soundbrain.ui.theme.Background
import com.jlls.soundbrain.ui.theme.SoundBrainTheme
import com.jlls.soundbrain.ui.theme.StatusActive
import com.jlls.soundbrain.ui.theme.StatusLowStock
import com.jlls.soundbrain.ui.theme.StatusOutOfStock

/**
 * Home screen composable displaying the dashboard feed.
 * Shows:
 * - Quick statistics cards (total products, active, low stock, out of stock)
 * - Recent activity feed
 *
 * @param viewModel HomeViewModel instance
 * @param modifier Modifier for the screen
 */
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 16.dp)
    ) {
        // Screen Title
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Dashboard",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Quick Stats Row
        item {
            StatsRow(
                stats = listOf(
                    "Total" to uiState.stats.totalProducts.toString(),
                    "Active" to uiState.stats.activeCount.toString(),
                    "Low Stock" to uiState.stats.lowStockCount.toString()
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Statistics Cards
        item {
            Text(
                text = "Inventory Overview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        item {
            StatCard(
                title = "Total Products",
                value = uiState.stats.totalProducts.toString(),
                icon = Icons.Default.Inventory,
                backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            StatCard(
                title = "Total Value",
                value = "$${String.format("%.2f", uiState.stats.totalValue)}",
                icon = Icons.Default.TrendingUp,
                backgroundColor = StatusActive.copy(alpha = 0.15f)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            StatCard(
                title = "Active Products",
                value = uiState.stats.activeCount.toString(),
                icon = Icons.Default.Add,
                backgroundColor = StatusActive.copy(alpha = 0.15f)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            StatCard(
                title = "Low Stock Items",
                value = uiState.stats.lowStockCount.toString(),
                icon = Icons.Default.Warning,
                backgroundColor = StatusLowStock.copy(alpha = 0.15f)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            StatCard(
                title = "Out of Stock",
                value = uiState.stats.outOfStockCount.toString(),
                icon = Icons.Default.Warning,
                backgroundColor = StatusOutOfStock.copy(alpha = 0.15f)
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Recent Activity Section
        item {
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(uiState.recentActivity) { activity ->
            ActivityItem(
                action = activity.action,
                productName = activity.productName,
                timestamp = activity.timestamp
            )
        }

        // Bottom padding for nav bar
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    SoundBrainTheme {
        HomeScreen()
    }
}