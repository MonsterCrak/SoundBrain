package com.jlls.soundbrain.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlls.soundbrain.data.repository.ProductRepositoryImpl
import com.jlls.soundbrain.domain.model.Product
import com.jlls.soundbrain.domain.usecase.GetProductByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Product Detail screen.
 * Manages detailed product view state.
 *
 * @property getProductByIdUseCase Use case for getting a product by ID
 */
class ProductDetailViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase = GetProductByIdUseCase(
        ProductRepositoryImpl()
    )
) : ViewModel() {

    // UI State
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    /**
     * Load product details by ID.
     * @param productId The product ID to load
     */
    fun loadProduct(productId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            getProductByIdUseCase(productId).collect { product ->
                _uiState.value = _uiState.value.copy(
                    product = product,
                    isLoading = false
                )
            }
        }
    }
}

/**
 * UI State for the Product Detail screen.
 */
data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)