package com.jlls.soundbrain.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jlls.soundbrain.domain.model.Product
import com.jlls.soundbrain.domain.model.ProductStatus
import com.jlls.soundbrain.ui.theme.StatusActive
import com.jlls.soundbrain.ui.theme.StatusLowStock
import com.jlls.soundbrain.ui.theme.StatusOutOfStock
import androidx.compose.ui.tooling.preview.Preview

/**
 * Product card component with Instagram-style design.
 * Features:
 * - Rounded corners (24.dp) as per spec
 * - Large product image
 * - Product name, price, and status tag
 * - Clean white card on soft background
 *
 * @param product The product data to display
 * @param onClick Callback when the card is clicked
 * @param modifier Modifier for the card
 */
@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column {
            // Product Image - Instagram style aspect ratio
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Product Category (small label)
                Text(
                    text = product.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Product Name
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description (single line)
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Price and Status Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Price
                    Text(
                        text = "$${String.format("%.2f", product.price)}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Status Badge
                    StatusBadge(status = product.status, stock = product.stock)
                }
            }
        }
    }
}

/**
 * Status badge component showing product stock status.
 * Color-coded based on status (Active=Green, LowStock=Amber, OutOfStock=Red).
 *
 * @param status The product status
 * @param stock The current stock quantity
 * @param modifier Modifier for the badge
 */
@Composable
fun StatusBadge(
    status: ProductStatus,
    stock: Int,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor, label) = when (status) {
        ProductStatus.ACTIVE -> Triple(
            StatusActive.copy(alpha = 0.15f),
            StatusActive,
            "In Stock"
        )
        ProductStatus.LOW_STOCK -> Triple(
            StatusLowStock.copy(alpha = 0.15f),
            StatusLowStock,
            "Low: $stock"
        )
        ProductStatus.OUT_OF_STOCK -> Triple(
            StatusOutOfStock.copy(alpha = 0.15f),
            StatusOutOfStock,
            "Out"
        )
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

/**
 * Compact product card for horizontal lists or smaller spaces.
 * Shows essential info: image, name, price, status.
 *
 * @param product The product data
 * @param onClick Callback when clicked
 * @param modifier Modifier for the card
 */
@Composable
fun ProductCardCompact(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$${String.format("%.2f", product.price)}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    StatusBadge(status = product.status, stock = product.stock)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    val sampleProduct = Product(
        id = "PROD-001",
        name = "Wireless Noise-Canceling Headphones",
        description = "Premium over-ear headphones with active noise cancellation.",
        category = "Electronics",
        price = 249.99,
        stock = 45,
        status = ProductStatus.ACTIVE,
        imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400"
    )
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        ProductCard(
            product = sampleProduct,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StatusBadgePreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        StatusBadge(status = ProductStatus.ACTIVE, stock = 45)
        Spacer(modifier = Modifier.height(8.dp))
        StatusBadge(status = ProductStatus.LOW_STOCK, stock = 8)
        Spacer(modifier = Modifier.height(8.dp))
        StatusBadge(status = ProductStatus.OUT_OF_STOCK, stock = 0)
    }
}
