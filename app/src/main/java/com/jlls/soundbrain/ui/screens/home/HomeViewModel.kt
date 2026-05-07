package com.jlls.soundbrain.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlls.soundbrain.data.mock.ActivityItem
import com.jlls.soundbrain.data.mock.MockDataSource
import com.jlls.soundbrain.domain.model.Product
import com.jlls.soundbrain.domain.model.ProductStatus
import com.jlls.soundbrain.domain.usecase.GetInventoryStatsUseCase
import com.jlls.soundbrain.domain.usecase.InventoryStats
import com.jlls.soundbrain.data.repository.ProductRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Home screen.
 * Manages dashboard statistics and recent activity feed.
 *
 * @property getStatsUseCase Use case for getting inventory statistics
 */
class HomeViewModel(
    private val getStatsUseCase: GetInventoryStatsUseCase = GetInventoryStatsUseCase(
        ProductRepositoryImpl()
    )
) : ViewModel() {

    // UI State for the home screen
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    /**
     * Load dashboard data including stats and recent activity.
     */
    private fun loadDashboardData() {
        viewModelScope.launch {
            getStatsUseCase().collect { stats ->
                _uiState.value = _uiState.value.copy(
                    stats = stats,
                    isLoading = false
                )
            }
        }
    }

    /**
     * Refresh dashboard data.
     */
    fun refresh() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        loadDashboardData()
    }
}

/**
 * UI State data class for the Home screen.
 */
data class HomeUiState(
    val stats: InventoryStats = InventoryStats(
        totalProducts = 0,
        totalValue = 0.0,
        activeCount = 0,
        lowStockCount = 0,
        outOfStockCount = 0
    ),
    val recentActivity: List<ActivityItem> = MockDataSource.recentActivity,
    val isLoading: Boolean = true
)