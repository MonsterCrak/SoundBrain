package com.jlls.soundbrain.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================================================
// NEXUS RETAIL INTELLIGENCE - INSTAGRAM-STYLE COLOR PALETTE
// ============================================================================
// Palette inspired by Instagram/People apps:
// - Soft backgrounds for comfortable viewing
// - Pure white cards for content focus
// - Single accent color (Deep Violet) for brand identity
// ============================================================================

// Primary Brand Colors - Deep Violet
val Primary = Color(0xFF6366F1)          // Electric Blue / Deep Violet
val PrimaryVariant = Color(0xFF4F46E5)   // Darker variant for pressed states
val OnPrimary = Color.White              // Text on primary color

// Background Colors - Soft & Clean
val Background = Color(0xFFF8F9FA)       // Soft gray background (#F8F9FA)
val Surface = Color.White                // Pure white cards
val SurfaceVariant = Color(0xFFF1F3F5)   // Slightly darker surface for contrast

// Text Colors
val OnBackground = Color(0xFF1A1A2E)    // Dark text on light background
val OnSurface = Color(0xFF1A1A2E)        // Dark text on white surface
val OnSurfaceVariant = Color(0xFF6B7280) // Muted text for secondary content

// Status Colors
val StatusActive = Color(0xFF10B981)     // Green for active products
val StatusLowStock = Color(0xFFF59E0B)   // Amber for low stock warning
val StatusOutOfStock = Color(0xFFEF4444) // Red for out of stock items

// Accent & Interactive Colors
val Accent = Primary                     // Main accent (same as Primary)
val AccentLight = Color(0xFFEEF2FF)      // Light accent for backgrounds
val Interactive = Color(0xFF6366F1)       // Interactive element color
val OnInteractive = Color.White          // Text on interactive elements

// Divider & Border Colors
val Divider = Color(0xFFE5E7EB)          // Light divider
val Border = Color(0xFFDBE1E7)           // Subtle borders

// Card & Elevation Colors
val CardBackground = Color.White         // White cards
val CardShadow = Color(0x1A000000)        // Subtle shadow (10% black)

// ============================================================================
// DARK THEME COLORS
// ============================================================================

val DarkPrimary = Color(0xFF818CF8)      // Lighter violet for dark mode
val DarkOnPrimary = Color(0xFF1E1B4B)    // Dark text on light primary
val DarkBackground = Color(0xFF0F0F23)   // Very dark blue-black
val DarkSurface = Color(0xFF1A1A2E)      // Dark surface
val DarkSurfaceVariant = Color(0xFF252540) // Darker variant
val DarkOnBackground = Color(0xFFF8FAFC) // Light text on dark
val DarkOnSurface = Color(0xFFF8FAFC)    // Light text on dark surface
val DarkOnSurfaceVariant = Color(0xFF9CA3AF) // Muted light text
val DarkDivider = Color(0xFF374151)      // Dark divider
val DarkBorder = Color(0xFF4B5563)       // Dark border

// Dark status colors
val DarkStatusActive = Color(0xFF34D399)  // Brighter green for dark mode
val DarkStatusLowStock = Color(0xFFFBBF24) // Brighter amber for dark mode
val DarkStatusOutOfStock = Color(0xFFF87171) // Brighter red for dark mode