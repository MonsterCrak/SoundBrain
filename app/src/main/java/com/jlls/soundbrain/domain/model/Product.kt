package com.jlls.soundbrain.domain.model

/**
 * Product domain model representing an inventory item.
 * This is the core business entity used throughout the app.
 *
 * @property id Unique identifier for the product
 * @property name Product display name
 * @property description Product description/details
 * @property category Product category (e.g., Electronics, Clothing, etc.)
 * @property price Current price in local currency
 * @property stock Current stock quantity
 * @property status Product status: ACTIVE, LOW_STOCK, OUT_OF_STOCK
 * @property imageUrl URL or resource identifier for product image
 * @property lastUpdated Timestamp of last update
 */
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val stock: Int,
    val status: ProductStatus,
    val imageUrl: String,
    val lastUpdated: Long = System.currentTimeMillis()
)

/**
 * Product status enumeration for inventory tracking.
 */
enum class ProductStatus {
    ACTIVE,
    LOW_STOCK,
    OUT_OF_STOCK
}