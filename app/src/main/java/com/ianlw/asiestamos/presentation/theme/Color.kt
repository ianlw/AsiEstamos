package com.ianlw.asiestamos.presentation.theme

import androidx.compose.ui.graphics.Color

// =============================================
// LIQUID GLASS THEME
// Deep dark with blue/violet gradients, translucent surfaces
// =============================================
object LiquidGlassColors {
    val Background = Color(0xFF0A0A0F)
    val BackgroundGradientStart = Color(0xFF0D0D1A)
    val BackgroundGradientEnd = Color(0xFF0A0A0F)
    val Surface = Color(0xFF1A1A2E)
    val SurfaceVariant = Color(0xFF16162A)
    val SurfaceOverlay = Color(0x331A1A2E) // translucent
    val CardBackground = Color(0x22FFFFFF) // glass effect
    val CardBorder = Color(0x33FFFFFF)
    val CardBorderHighlight = Color(0x55818CF8)

    val Primary = Color(0xFF818CF8) // Soft violet
    val PrimaryVariant = Color(0xFF6366F1)
    val Secondary = Color(0xFF38BDF8) // Sky blue
    val Tertiary = Color(0xFFA78BFA) // Purple
    val Accent = Color(0xFF818CF8)

    val OnBackground = Color(0xFFF1F5F9)
    val OnSurface = Color(0xFFE2E8F0)
    val OnSurfaceVariant = Color(0xFF94A3B8)
    val OnPrimary = Color(0xFFFFFFFF)

    val TextPrimary = Color(0xFFF8FAFC)
    val TextSecondary = Color(0xFF94A3B8)
    val TextTertiary = Color(0xFF64748B)

    val Success = Color(0xFF34D399)
    val Warning = Color(0xFFFBBF24)
    val Error = Color(0xFFF87171)
    val Info = Color(0xFF38BDF8)

    // Category colors
    val Alimentacion = Color(0xFFFF6B6B)
    val Transporte = Color(0xFF4ECDC4)
    val Salud = Color(0xFFFF85A2)
    val Entretenimiento = Color(0xFFFFD93D)
    val Educacion = Color(0xFF6BCB77)
    val Hogar = Color(0xFF4D96FF)
    val Ropa = Color(0xFFCC5DE8)
    val Tecnologia = Color(0xFF38BDF8)
    val Otros = Color(0xFF94A3B8)
}

// =============================================
// FINTECH DARK THEME
// Pure black + neon green/cyan accents
// =============================================
object FintechDarkColors {
    val Background = Color(0xFF000000)
    val BackgroundGradientStart = Color(0xFF000000)
    val BackgroundGradientEnd = Color(0xFF0A0A0A)
    val Surface = Color(0xFF111111)
    val SurfaceVariant = Color(0xFF1A1A1A)
    val SurfaceOverlay = Color(0x22111111)
    val CardBackground = Color(0xFF141414)
    val CardBorder = Color(0xFF2A2A2A)
    val CardBorderHighlight = Color(0xFF00FF87)

    val Primary = Color(0xFF00FF87) // Neon green
    val PrimaryVariant = Color(0xFF00CC6A)
    val Secondary = Color(0xFF00D4FF) // Cyan
    val Tertiary = Color(0xFF00FF87)
    val Accent = Color(0xFF00FF87)

    val OnBackground = Color(0xFFFFFFFF)
    val OnSurface = Color(0xFFE0E0E0)
    val OnSurfaceVariant = Color(0xFF808080)
    val OnPrimary = Color(0xFF000000)

    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFF808080)
    val TextTertiary = Color(0xFF555555)

    val Success = Color(0xFF00FF87)
    val Warning = Color(0xFFFFD600)
    val Error = Color(0xFFFF4444)
    val Info = Color(0xFF00D4FF)

    val Alimentacion = Color(0xFFFF6B6B)
    val Transporte = Color(0xFF00D4FF)
    val Salud = Color(0xFFFF69B4)
    val Entretenimiento = Color(0xFFFFD600)
    val Educacion = Color(0xFF00FF87)
    val Hogar = Color(0xFF4D96FF)
    val Ropa = Color(0xFFCC5DE8)
    val Tecnologia = Color(0xFF00D4FF)
    val Otros = Color(0xFF808080)
}

// =============================================
// MINIMAL PAPER THEME
// Near-white, bold black typography, warm amber accent
// =============================================
object MinimalPaperColors {
    val Background = Color(0xFFFAF9F6)
    val BackgroundGradientStart = Color(0xFFFAF9F6)
    val BackgroundGradientEnd = Color(0xFFF5F3EF)
    val Surface = Color(0xFFFFFFFF)
    val SurfaceVariant = Color(0xFFF5F3EF)
    val SurfaceOverlay = Color(0x11000000)
    val CardBackground = Color(0xFFFFFFFF)
    val CardBorder = Color(0xFFE8E5DF)
    val CardBorderHighlight = Color(0xFFD97706)

    val Primary = Color(0xFFD97706) // Amber
    val PrimaryVariant = Color(0xFFB45309)
    val Secondary = Color(0xFFC2410C) // Terracotta
    val Tertiary = Color(0xFFD97706)
    val Accent = Color(0xFFD97706)

    val OnBackground = Color(0xFF1A1A1A)
    val OnSurface = Color(0xFF2D2D2D)
    val OnSurfaceVariant = Color(0xFF737373)
    val OnPrimary = Color(0xFFFFFFFF)

    val TextPrimary = Color(0xFF1A1A1A)
    val TextSecondary = Color(0xFF737373)
    val TextTertiary = Color(0xFFA3A3A3)

    val Success = Color(0xFF16A34A)
    val Warning = Color(0xFFD97706)
    val Error = Color(0xFFDC2626)
    val Info = Color(0xFF2563EB)

    val Alimentacion = Color(0xFFDC2626)
    val Transporte = Color(0xFF0891B2)
    val Salud = Color(0xFFDB2777)
    val Entretenimiento = Color(0xFFD97706)
    val Educacion = Color(0xFF16A34A)
    val Hogar = Color(0xFF2563EB)
    val Ropa = Color(0xFF9333EA)
    val Tecnologia = Color(0xFF0891B2)
    val Otros = Color(0xFF737373)
}

/**
 * Returns the color for a given category based on the current theme.
 */
fun categoryColor(categoria: String, theme: ThemeType): Color {
    val colors = when (theme) {
        ThemeType.LIQUID_GLASS, ThemeType.MATERIAL_YOU -> LiquidGlassColors
        ThemeType.FINTECH_DARK -> FintechDarkColors
        ThemeType.MINIMAL_PAPER -> MinimalPaperColors
    }
    return when (categoria) {
        "Alimentación" -> colors.Alimentacion
        "Transporte" -> colors.Transporte
        "Salud" -> colors.Salud
        "Entretenimiento" -> colors.Entretenimiento
        "Educación" -> colors.Educacion
        "Hogar" -> colors.Hogar
        "Ropa" -> colors.Ropa
        "Tecnología" -> colors.Tecnologia
        else -> colors.Otros
    }
}
