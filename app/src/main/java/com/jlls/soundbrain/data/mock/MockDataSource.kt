package com.jlls.soundbrain.data.mock

import com.jlls.soundbrain.domain.model.Product
import com.jlls.soundbrain.domain.model.ProductStatus

/**
 * Mock data source providing sample product data for the inventory app.
 * In production, this would be replaced with actual API calls or database queries.
 *
 * Contains 10 sample products with varied categories, prices, and stock statuses.
 */
object MockDataSource {

    /**
     * Get the list of mock products.
     * Products are designed to showcase the Instagram-style inventory UI.
     */
    val products: List<Product> = listOf(
        Product(
            id = "PROD-001",
            name = "Wireless Noise-Canceling Headphones",
            description = "Premium over-ear headphones with active noise cancellation, 30-hour battery life, and superior sound quality.",
            category = "Electronics",
            price = 249.99,
            stock = 45,
            status = ProductStatus.ACTIVE,
            imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400"
        ),
        Product(
            id = "PROD-002",
            name = "Minimalist Leather Watch",
            description = "Classic design with genuine leather strap, stainless steel case, and water resistance up to 50 meters.",
            category = "Accessories",
            price = 189.00,
            stock = 12,
            status = ProductStatus.ACTIVE,
            imageUrl = "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400"
        ),
        Product(
            id = "PROD-003",
            name = "Organic Cotton T-Shirt",
            description = "Sustainable comfort tee made from 100% GOTS certified organic cotton. Soft, breathable, eco-friendly.",
            category = "Clothing",
            price = 34.99,
            stock = 8,
            status = ProductStatus.LOW_STOCK,
            imageUrl = "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400"
        ),
        Product(
            id = "PROD-004",
            name = "Smart Home Speaker",
            description = "Voice-controlled speaker with built-in AI assistant, premium audio, and smart home hub integration.",
            category = "Electronics",
            price = 129.95,
            stock = 0,
            status = ProductStatus.OUT_OF_STOCK,
            imageUrl = "https://images.unsplash.com/photo-1543512214-318c7553f230?w=400"
        ),
        Product(
            id = "PROD-005",
            name = "Ceramic Pour-Over Coffee Set",
            description = "Handcrafted ceramic dripper with matching carafe. Perfect for single-serve pour-over coffee at home.",
            category = "Home & Kitchen",
            price = 68.00,
            stock = 23,
            status = ProductStatus.ACTIVE,
            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400"
        ),
        Product(
            id = "PROD-006",
            name = "Running Performance Shoes",
            description = "Lightweight running shoes with responsive cushioning, breathable mesh upper, and durable rubber outsole.",
            category = "Sports",
            price = 145.00,
            stock = 5,
            status = ProductStatus.LOW_STOCK,
            imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400"
        ),
        Product(
            id = "PROD-007",
            name = "Portable Bluetooth Speaker",
            description = "Waterproof speaker with 360° sound, 24-hour battery, and built-in microphone for calls.",
            category = "Electronics",
            price = 79.99,
            stock = 67,
            status = ProductStatus.ACTIVE,
            imageUrl = "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=400"
        ),
        Product(
            id = "PROD-008",
            name = "Bamboo Desktop Organizer",
            description = "Sustainable bamboo desk organizer with multiple compartments for pens, phone, and accessories.",
            category = "Home & Office",
            price = 45.50,
            stock = 0,
            status = ProductStatus.OUT_OF_STOCK,
            imageUrl = "https://images.unsplash.com/photo-1544816155-12df9643f363?w=400"
        ),
        Product(
            id = "PROD-009",
            name = "Vintage Polaroid Camera",
            description = "Instant film camera with built-in flash, automatic exposure, and classic Polaroid aesthetic.",
            category = "Photography",
            price = 89.99,
            stock = 31,
            status = ProductStatus.ACTIVE,
            imageUrl = "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=400"
        ),
        Product(
            id = "PROD-010",
            name = "Artisan Scented Candle Set",
            description = "Set of 3 hand-poured soy candles with natural essential oils. Scents: Lavender, Sandalwood, Citrus.",
            category = "Home & Lifestyle",
            price = 42.00,
            stock = 15,
            status = ProductStatus.ACTIVE,
            imageUrl = "https://images.unsplash.com/photo-1602607434770-38e2407602ed?w=400"
        )
    )

    /**
     * Get recent activity items for the home feed.
     * Simulates inventory changes and updates.
     */
    val recentActivity: List<ActivityItem> = listOf(
        ActivityItem("Added 50 units", "Wireless Headphones", "2 hours ago"),
        ActivityItem("Low stock alert", "Cotton T-Shirt", "5 hours ago"),
        ActivityItem("Price updated", "Smart Speaker", "Yesterday"),
        ActivityItem("Out of stock", "Desktop Organizer", "Yesterday"),
        ActivityItem("New product", "Scented Candle Set", "2 days ago")
    )
}

/**
 * Data class representing a recent activity item for the home feed.
 */
data class ActivityItem(
    val action: String,
    val productName: String,
    val timestamp: String
)