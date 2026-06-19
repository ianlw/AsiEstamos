package com.ianlw.asiestamos.presentation.theme

import android.os.Build
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.ianlw.asiestamos.data.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Custom composition local to access theme type from anywhere
val LocalThemeType = compositionLocalOf { ThemeType.LIQUID_GLASS }

// === COLOR SCHEMES ===

private val LiquidGlassColorScheme = darkColorScheme(
    primary = LiquidGlassColors.Primary,
    onPrimary = LiquidGlassColors.OnPrimary,
    primaryContainer = LiquidGlassColors.PrimaryVariant,
    secondary = LiquidGlassColors.Secondary,
    tertiary = LiquidGlassColors.Tertiary,
    background = LiquidGlassColors.Background,
    onBackground = LiquidGlassColors.OnBackground,
    surface = LiquidGlassColors.Surface,
    onSurface = LiquidGlassColors.OnSurface,
    surfaceVariant = LiquidGlassColors.SurfaceVariant,
    onSurfaceVariant = LiquidGlassColors.OnSurfaceVariant,
    error = LiquidGlassColors.Error,
    outline = LiquidGlassColors.CardBorder,
    outlineVariant = LiquidGlassColors.CardBorderHighlight,
)

private val FintechDarkColorScheme = darkColorScheme(
    primary = FintechDarkColors.Primary,
    onPrimary = FintechDarkColors.OnPrimary,
    primaryContainer = FintechDarkColors.PrimaryVariant,
    secondary = FintechDarkColors.Secondary,
    tertiary = FintechDarkColors.Tertiary,
    background = FintechDarkColors.Background,
    onBackground = FintechDarkColors.OnBackground,
    surface = FintechDarkColors.Surface,
    onSurface = FintechDarkColors.OnSurface,
    surfaceVariant = FintechDarkColors.SurfaceVariant,
    onSurfaceVariant = FintechDarkColors.OnSurfaceVariant,
    error = FintechDarkColors.Error,
    outline = FintechDarkColors.CardBorder,
    outlineVariant = FintechDarkColors.CardBorderHighlight,
)

private val MinimalPaperColorScheme = lightColorScheme(
    primary = MinimalPaperColors.Primary,
    onPrimary = MinimalPaperColors.OnPrimary,
    primaryContainer = MinimalPaperColors.PrimaryVariant,
    secondary = MinimalPaperColors.Secondary,
    tertiary = MinimalPaperColors.Tertiary,
    background = MinimalPaperColors.Background,
    onBackground = MinimalPaperColors.OnBackground,
    surface = MinimalPaperColors.Surface,
    onSurface = MinimalPaperColors.OnSurface,
    surfaceVariant = MinimalPaperColors.SurfaceVariant,
    onSurfaceVariant = MinimalPaperColors.OnSurfaceVariant,
    error = MinimalPaperColors.Error,
    outline = MinimalPaperColors.CardBorder,
    outlineVariant = MinimalPaperColors.CardBorderHighlight,
)

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {
    val themeType = userPreferences.temaSeleccionado.map { ThemeType.fromString(it) }
}

@Composable
fun AppTheme(
    themeViewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val themeType by themeViewModel.themeType.collectAsState(initial = ThemeType.LIQUID_GLASS)

    Crossfade(
        targetState = themeType,
        animationSpec = tween(400),
        label = "theme_crossfade"
    ) { currentTheme ->
        val colorScheme = resolveColorScheme(currentTheme)
        val typography = resolveTypography(currentTheme)
        val shapes = resolveShapes(currentTheme)

        CompositionLocalProvider(LocalThemeType provides currentTheme) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = typography,
                shapes = shapes,
                content = content
            )
        }
    }
}

@Composable
private fun resolveColorScheme(themeType: ThemeType): ColorScheme {
    return when (themeType) {
        ThemeType.LIQUID_GLASS -> LiquidGlassColorScheme
        ThemeType.FINTECH_DARK -> FintechDarkColorScheme
        ThemeType.MINIMAL_PAPER -> MinimalPaperColorScheme
        ThemeType.MATERIAL_YOU -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val context = LocalContext.current
                if (isSystemInDarkTheme()) {
                    dynamicDarkColorScheme(context)
                } else {
                    dynamicLightColorScheme(context)
                }
            } else {
                // Fallback to Liquid Glass if dynamic colors not available
                LiquidGlassColorScheme
            }
        }
    }
}

private fun resolveTypography(themeType: ThemeType) = when (themeType) {
    ThemeType.LIQUID_GLASS -> LiquidGlassTypography
    ThemeType.FINTECH_DARK -> FintechDarkTypography
    ThemeType.MINIMAL_PAPER -> MinimalPaperTypography
    ThemeType.MATERIAL_YOU -> MaterialYouTypography
}

private fun resolveShapes(themeType: ThemeType) = when (themeType) {
    ThemeType.LIQUID_GLASS -> LiquidGlassShapes
    ThemeType.FINTECH_DARK -> FintechDarkShapes
    ThemeType.MINIMAL_PAPER -> MinimalPaperShapes
    ThemeType.MATERIAL_YOU -> MaterialYouShapes
}
