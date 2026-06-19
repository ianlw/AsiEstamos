package com.ianlw.asiestamos.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ianlw.asiestamos.domain.model.Categoria
import com.ianlw.asiestamos.presentation.theme.LocalThemeType
import com.ianlw.asiestamos.presentation.theme.categoryColor

@Composable
fun CategoryChip(
    categoria: String,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val themeType = LocalThemeType.current
    val color = categoryColor(categoria, themeType)
    val cat = Categoria.fromValor(categoria)

    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = "${cat.emoji} ${cat.valor}",
                style = MaterialTheme.typography.labelMedium
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = color.copy(alpha = 0.1f),
            labelColor = color,
            selectedContainerColor = color.copy(alpha = 0.25f),
            selectedLabelColor = color
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = color.copy(alpha = 0.3f),
            selectedBorderColor = color.copy(alpha = 0.6f),
            enabled = true,
            selected = selected
        ),
        modifier = modifier
    )
}
