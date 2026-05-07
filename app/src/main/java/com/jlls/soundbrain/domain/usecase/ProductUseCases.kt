package com.jlls.soundbrain.domain.usecase

import com.jlls.soundbrain.domain.model.Product
import com.jlls.soundbrain.domain.model.ProductStatus
import com.jlls.soundbrain.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case for retrieving all products from inventory.
 * Follows single responsibility principle - one use case per business action.
 *
 * @property repository Product data repository
 */
class GetProductsUseCase(private val repository: ProductRepository) {

    /**
     * Execute the use case to get all products.
     * @return Flow of List of Products
     */
    operator fun invoke(): Flow<List<Product>> = repository.getProducts()
}

/**
 * Use case for retrieving a single product by ID.
 *
 * @property repository Product data repository
 */
class GetProductByIdUseCase(private val repository: ProductRepository) {

    /**
     * Execute the use case to get a product by ID.
     * @param productId The unique product identifier
     * @return Flow emitting the Product if found, or null
     */
    operator fun invoke(productId: String): Flow<Product?> = repository.getProductById(productId)
}

/**
 * Use case for getting quick statistics about inventory.
 * Provides summary data for dashboard display.
 *
 * @property repository Product data repository
 */
class GetInventoryStatsUseCase(private val repository: ProductRepository) {

    /**
     * Execute the use case to get inventory statistics.
     * @return Flow of InventoryStats data class
     */
    operator fun invoke(): Flow<InventoryStats> = repository.getProducts().let { productsFlow ->
        productsFlow.map { products ->
            InventoryStats(
                totalProducts = products.size,
                totalValue = products.sumOf { it.price * it.stock },
                activeCount = products.count { it.status == ProductStatus.ACTIVE },
                lowStockCount = products.count { it.status == ProductStatus.LOW_STOCK },
                outOfStockCount = products.count { it.status == ProductStatus.OUT_OF_STOCK }
            )
        }
    }
}

/**
 * Data class representing inventory statistics for dashboard.
 */
data class InventoryStats(
    val totalProducts: Int,
    val totalValue: Double,
    val activeCount: Int,
    val lowStockCount: Int,
    val outOfStockCount: Int
)