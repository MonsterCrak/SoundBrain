package com.jlls.soundbrain.ui.screens.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlls.soundbrain.data.repository.ProductRepositoryImpl
import com.jlls.soundbrain.domain.model.Product
import com.jlls.soundbrain.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Inventory screen.
 * Manages product list state with StateFlow (not remember).
 *
 * @property getProductsUseCase Use case for getting products
 */
class InventoryViewModel(
    private val getProductsUseCase: GetProductsUseCase = GetProductsUseCase(
        ProductRepositoryImpl()
    )
) : ViewModel() {

    // UI State - StateFlow for reactive state management
    private val _uiState = MutableStateFlow(InventoryUiState())
    val uiState: StateFlow<InventoryUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    /**
     * Load products from the repository.
     * Updates UI state with loading, success, or error states.
     */
    private fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                getProductsUseCase().collect { products ->
                    _uiState.value = _uiState.value.copy(
                        products = products,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    /**
     * Select a product for detail view.
     * @param productId The ID of the product to select
     */
    fun selectProduct(productId: String) {
        val product = _uiState.value.products.find { it.id == productId }
        _uiState.value = _uiState.value.copy(selectedProduct = product)
    }

    /**
     * Clear product selection.
     */
    fun clearSelection() {
        _uiState.value = _uiState.value.copy(selectedProduct = null)
    }

    /**
     * Filter products by search query.
     * @param query Search string
     */
    fun searchProducts(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    /**
     * Get filtered products based on search query.
     */
    val filteredProducts: List<Product>
        get() {
            val query = _uiState.value.searchQuery.lowercase()
            return if (query.isEmpty()) {
                _uiState.value.products
            } else {
                _uiState.value.products.filter {
                    it.name.lowercase().contains(query) ||
                    it.category.lowercase().contains(query)
                }
            }
        }
}

/**
 * UI State data class for the Inventory screen.
 */
data class InventoryUiState(
    val products: List<Product> = emptyList(),
    val selectedProduct: Product? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val error: String? = null
)