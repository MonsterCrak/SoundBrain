package com.jlls.soundbrain.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ============================================================================
// NEXUS RETAIL INTELLIGENCE - MATERIAL 3 THEME
// ============================================================================
// Instagram-style theme with:
// - Soft light background for comfortable viewing
// - Deep Violet as primary accent color
// - Clean white cards for content hierarchy
// - Proper dark mode support
// ============================================================================

/**
 * Light color scheme for the app.
 * Uses soft gray background (#F8F9FA) and white cards.
 */
private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = AccentLight,
    onPrimaryContainer = Primary,

    secondary = PrimaryVariant,
    onSecondary = OnPrimary,
    secondaryContainer = AccentLight,
    onSecondaryContainer = PrimaryVariant,

    tertiary = StatusActive,
    onTertiary = OnPrimary,
    tertiaryContainer = StatusActive.copy(alpha = 0.1f),
    onTertiaryContainer = StatusActive,

    error = StatusOutOfStock,
    onError = OnPrimary,
    errorContainer = StatusOutOfStock.copy(alpha = 0.1f),
    onErrorContainer = StatusOutOfStock,

    background = Background,
    onBackground = OnBackground,

    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,

    outline = Border,
    outlineVariant = Divider,

    // Inverse colors for surfaces that need inversion
    inverseSurface = DarkSurface,
    inverseOnSurface = DarkOnSurface,
    inversePrimary = DarkPrimary
)

/**
 * Dark color scheme for the app.
 * Uses dark blue-black background with lighter text.
 */
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = Primary,
    onPrimaryContainer = DarkPrimary,

    secondary = DarkPrimary,
    onSecondary = DarkOnPrimary,
    secondaryContainer = PrimaryVariant,
    onSecondaryContainer = DarkPrimary,

    tertiary = DarkStatusActive,
    onTertiary = DarkOnPrimary,
    tertiaryContainer = DarkStatusActive.copy(alpha = 0.2f),
    onTertiaryContainer = DarkStatusActive,

    error = DarkStatusOutOfStock,
    onError = DarkOnPrimary,
    errorContainer = DarkStatusOutOfStock.copy(alpha = 0.2f),
    onErrorContainer = DarkStatusOutOfStock,

    background = DarkBackground,
    onBackground = DarkOnBackground,

    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,

    outline = DarkBorder,
    outlineVariant = DarkDivider,

    inverseSurface = Surface,
    inverseOnSurface = OnSurface,
    inversePrimary = Primary
)

/**
 * Main theme composable for the Nexus Retail Intelligence app.
 *
 * @param darkTheme Whether to use dark theme (default: follow system)
 * @param dynamicColor Whether to use dynamic color from Material You (Android 12+)
 * @param content The content to apply the theme to
 */
@Composable
fun NexusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disabled for consistent branding
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Dynamic color is disabled to maintain consistent Instagram-style branding
        // Enable this on Android 12+ if you want Material You colors
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * Alias for SoundBrainTheme to maintain backward compatibility.
 * Use NexusTheme for new code.
 */
@Composable
fun SoundBrainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    NexusTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
}