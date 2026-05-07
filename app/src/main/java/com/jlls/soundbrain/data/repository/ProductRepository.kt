package com.jlls.soundbrain.data.repository

import com.jlls.soundbrain.data.mock.MockDataSource
import com.jlls.soundbrain.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository interface for product data operations.
 * Defines the contract for data access (dependency inversion).
 */
interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    fun getProductById(productId: String): Flow<Product?>
}

/**
 * Implementation of ProductRepository using mock data.
 * In production, this would connect to a real data source (API, database).
 *
 * @param mockDataSource Source of mock product data
 */
class ProductRepositoryImpl(
    private val mockDataSource: MockDataSource = MockDataSource
) : ProductRepository {

    /**
     * Get all products as a Flow.
     * Using Flow for reactive data handling with coroutines.
     */
    override fun getProducts(): Flow<List<Product>> = flow {
        emit(mockDataSource.products)
    }

    /**
     * Get a single product by its ID.
     * @param productId Unique identifier of the product
     * @return Flow emitting the Product if found, null otherwise
     */
    override fun getProductById(productId: String): Flow<Product?> = flow {
        emit(mockDataSource.products.find { it.id == productId })
    }
}