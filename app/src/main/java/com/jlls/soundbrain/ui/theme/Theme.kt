package com.jlls.soundbrain.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ============================================================================
// SOUNDBRAIN MUSIC AI - MATERIAL 3 THEME
// ============================================================================
// Zen-minimalism theme with:
// - Soft violet primary for brand identity
// - Light airy background for Zen aesthetic
// - Glassmorphism support
// - Clean dark mode support
// ============================================================================

/**
 * Light color scheme for SoundBrain.
 * Uses very light gray background (#FAFBFC) and white cards.
 */
private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = Primary.copy(alpha = 0.1f),
    onPrimaryContainer = Primary,

    secondary = AccentCyan,
    onSecondary = OnPrimary,
    secondaryContainer = AccentCyan.copy(alpha = 0.1f),
    onSecondaryContainer = AccentCyan,

    tertiary = AccentPink,
    onTertiary = OnPrimary,
    tertiaryContainer = AccentPink.copy(alpha = 0.1f),
    onTertiaryContainer = AccentPink,

    error = Color(0xFFEF4444),
    onError = OnPrimary,
    errorContainer = Color(0xFFEF4444).copy(alpha = 0.1f),
    onErrorContainer = Color(0xFFEF4444),

    background = Background,
    onBackground = OnBackground,

    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,

    outline = Border,
    outlineVariant = Divider,

    inverseSurface = DarkSurface,
    inverseOnSurface = DarkOnSurface,
    inversePrimary = DarkPrimary
)

/**
 * Dark color scheme for SoundBrain.
 * Uses dark background with lighter text.
 */
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimary.copy(alpha = 0.2f),
    onPrimaryContainer = DarkPrimary,

    secondary = AccentCyan,
    onSecondary = OnPrimary,
    secondaryContainer = AccentCyan.copy(alpha = 0.2f),
    onSecondaryContainer = AccentCyan,

    tertiary = AccentPink,
    onTertiary = OnPrimary,
    tertiaryContainer = AccentPink.copy(alpha = 0.2f),
    onTertiaryContainer = AccentPink,

    error = Color(0xFFF87171),
    onError = DarkOnPrimary,
    errorContainer = Color(0xFFF87171).copy(alpha = 0.2f),
    onErrorContainer = Color(0xFFF87171),

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
 * Main theme composable for SoundBrain Music AI.
 *
 * @param darkTheme Whether to use dark theme (default: follow system)
 * @param content The content to apply the theme to
 */
@Composable
fun SoundBrainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

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
