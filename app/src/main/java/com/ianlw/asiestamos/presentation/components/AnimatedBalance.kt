package com.ianlw.asiestamos.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AnimatedBalance(
    amount: Double,
    prefix: String = "S/ ",
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayMedium,
    color: Color = MaterialTheme.colorScheme.onBackground,
    duration: Int = 800
) {
    var targetValue by remember { mutableFloatStateOf(0f) }
    val animatedValue by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = duration),
        label = "balance_animation"
    )

    LaunchedEffect(amount) {
        targetValue = amount.toFloat()
    }

    val formatter = remember { NumberFormat.getNumberInstance(Locale("es", "PE")).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }}

    Text(
        text = "$prefix${formatter.format(animatedValue.toDouble())}",
        style = style,
        color = color,
        modifier = modifier
    )
}
