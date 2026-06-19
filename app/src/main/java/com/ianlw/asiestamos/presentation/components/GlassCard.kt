package com.ianlw.asiestamos.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ianlw.asiestamos.presentation.theme.LiquidGlassColors
import com.ianlw.asiestamos.presentation.theme.LocalThemeType
import com.ianlw.asiestamos.presentation.theme.ThemeType

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val themeType = LocalThemeType.current
    val shape = MaterialTheme.shapes.large

    val backgroundColor = when (themeType) {
        ThemeType.LIQUID_GLASS -> LiquidGlassColors.CardBackground
        ThemeType.FINTECH_DARK -> MaterialTheme.colorScheme.surface
        ThemeType.MATERIAL_YOU -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ThemeType.MINIMAL_PAPER -> MaterialTheme.colorScheme.surface
    }

    val borderColor = when (themeType) {
        ThemeType.LIQUID_GLASS -> LiquidGlassColors.CardBorder
        ThemeType.FINTECH_DARK -> MaterialTheme.colorScheme.outline
        ThemeType.MATERIAL_YOU -> Color.Transparent
        ThemeType.MINIMAL_PAPER -> MaterialTheme.colorScheme.outline
    }

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .background(
                color = backgroundColor,
                shape = shape
            )
            .padding(16.dp)
    ) {
        content()
    }
}
