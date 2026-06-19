package com.ianlw.asiestamos.presentation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.ianlw.asiestamos.presentation.components.PageIndicator
import com.ianlw.asiestamos.presentation.estadisticas.EstadisticasScreen
import com.ianlw.asiestamos.presentation.historial.HistorialScreen
import com.ianlw.asiestamos.presentation.home.HomeScreen
import com.ianlw.asiestamos.presentation.theme.LiquidGlassColors
import com.ianlw.asiestamos.presentation.theme.LocalThemeType
import com.ianlw.asiestamos.presentation.theme.ThemeType

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(
        initialPage = 1, // Start on Home
        pageCount = { 3 }
    )
    val themeType = LocalThemeType.current

    val backgroundBrush = when (themeType) {
        ThemeType.LIQUID_GLASS -> Brush.verticalGradient(
            colors = listOf(
                LiquidGlassColors.BackgroundGradientStart,
                LiquidGlassColors.Background,
                LiquidGlassColors.BackgroundGradientEnd
            )
        )
        else -> Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.background
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                snapAnimationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) { page ->
            when (page) {
                0 -> HistorialScreen()
                1 -> HomeScreen()
                2 -> EstadisticasScreen()
            }
        }

        // Page indicator at bottom
        PageIndicator(
            pageCount = 3,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}
