package com.ianlw.asiestamos.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun AudioWaveform(
    isRecording: Boolean,
    modifier: Modifier = Modifier,
    barColor: Color = MaterialTheme.colorScheme.primary,
    barCount: Int = 32
) {
    var amplitudes by remember { mutableStateOf(List(barCount) { 0.1f }) }

    LaunchedEffect(isRecording) {
        while (isRecording) {
            amplitudes = List(barCount) {
                if (isRecording) Random.nextFloat() * 0.7f + 0.1f else 0.1f
            }
            delay(80)
        }
        // Reset when stopped
        amplitudes = List(barCount) { 0.1f }
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        val barWidth = size.width / (barCount * 2f)
        val spacing = barWidth
        val centerY = size.height / 2f

        amplitudes.forEachIndexed { index, amplitude ->
            val barHeight = size.height * amplitude
            val x = index * (barWidth + spacing)
            val topY = centerY - barHeight / 2

            drawRoundRect(
                color = barColor.copy(alpha = 0.5f + amplitude * 0.5f),
                topLeft = Offset(x, topY),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2, barWidth / 2)
            )
        }
    }
}
