package com.jlls.soundbrain.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jlls.soundbrain.domain.model.Product
import com.jlls.soundbrain.domain.model.ProductStatus
import com.jlls.soundbrain.ui.components.StatusBadge
import com.jlls.soundbrain.ui.components.ShimmerEffect
import com.jlls.soundbrain.ui.theme.Background
import com.jlls.soundbrain.ui.theme.Primary

/**
 * Product Detail screen - immersive view with large image and full details.
 * Features:
 * - Large hero image
 * - Product info with status
 * - Action buttons
 *
 * @param productId The product ID to display
 * @param viewModel ProductDetailViewModel instance
 * @param onBack Callback for back navigation
 * @param modifier Modifier for the screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    viewModel: ProductDetailViewModel = viewModel(),
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background
                )
            )
        },
        containerColor = Background
    ) { innerPadding ->
        if (uiState.isLoading) {
            // Loading state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                ShimmerEffect(height = 300, widthFactor = 1f)
                Spacer(modifier = Modifier.height(16.dp))
                ShimmerEffect(widthFactor = 0.7f, height = 24)
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerEffect(widthFactor = 0.5f, height = 20)
            }
        } else {
            uiState.product?.let { product ->
                ProductDetailContent(
                    product = product,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

/**
 * Product detail content composable.
 */
@Composable
private fun ProductDetailContent(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Hero Image - Full width, large
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Category and Status Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.category.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.weight(1f))

                StatusBadge(status = product.status, stock = product.stock)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Product Name - Large title
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Price
            Text(
                text = "$${String.format("%.2f", product.price)}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Stock Info Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Stock Quantity",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${product.stock} units",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                StatusBadge(
                    status = product.status,
                    stock = product.stock,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons
            Button(
                onClick = { /* Edit action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Edit Product",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(80.dp)) // Bottom nav padding
        }
    }
}