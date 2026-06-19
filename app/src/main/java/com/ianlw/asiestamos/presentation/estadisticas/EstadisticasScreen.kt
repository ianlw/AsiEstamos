package com.ianlw.asiestamos.presentation.estadisticas

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ianlw.asiestamos.domain.model.Categoria
import com.ianlw.asiestamos.presentation.components.GlassCard
import com.ianlw.asiestamos.presentation.components.KpiCard
import com.ianlw.asiestamos.presentation.theme.LocalThemeType
import com.ianlw.asiestamos.presentation.theme.categoryColor
import java.text.NumberFormat
import java.util.Locale

@Composable
fun EstadisticasScreen(
    viewModel: EstadisticasViewModel = hiltViewModel()
) {
    val filtro by viewModel.filtro.collectAsState()
    val stats by viewModel.estadisticas.collectAsState()
    val themeType = LocalThemeType.current
    val formatter = NumberFormat.getNumberInstance(Locale("es", "PE")).apply {
        minimumFractionDigits = 2; maximumFractionDigits = 2
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Estadísticas",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        // Time filter chips
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                FiltroTiempo.entries.forEach { f ->
                    FilterChip(
                        selected = filtro == f,
                        onClick = { viewModel.setFiltro(f) },
                        label = { Text(f.label) }
                    )
                }
            }
        }

        // KPI Cards
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                KpiCard(
                    icon = Icons.Default.AttachMoney,
                    label = "Total gastado",
                    value = "S/ ${formatter.format(stats.totalGastado)}",
                    modifier = Modifier.width(160.dp)
                )
                KpiCard(
                    icon = Icons.Default.Category,
                    label = "Categoría top",
                    value = stats.categoriaTop?.categoria ?: "—",
                    modifier = Modifier.width(160.dp)
                )
                KpiCard(
                    icon = Icons.Default.TrendingUp,
                    label = "Promedio diario",
                    value = "S/ ${formatter.format(stats.promedioDiario)}",
                    modifier = Modifier.width(160.dp)
                )
                KpiCard(
                    icon = Icons.Default.Percent,
                    label = "Presupuesto",
                    value = "${formatter.format(stats.porcentajePresupuesto)}%",
                    modifier = Modifier.width(160.dp)
                )
            }
        }

        // Category breakdown
        item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        text = "Gasto por categoría",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    if (stats.gastosPorCategoria.isEmpty()) {
                        Text(
                            text = "Sin datos para el período",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        val maxMonto = stats.gastosPorCategoria.maxOfOrNull { it.monto } ?: 1.0
                        stats.gastosPorCategoria.forEach { catMonto ->
                            val fraction = (catMonto.monto / maxMonto).toFloat()
                            val color = categoryColor(catMonto.categoria, themeType)
                            val cat = Categoria.fromValor(catMonto.categoria)

                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${cat.emoji} ${cat.valor}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "S/ ${formatter.format(catMonto.monto)}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                // Progress bar
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(6.dp)
                                ) {
                                    // Background
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(end = 0.dp)
                                    ) {
                                        androidx.compose.foundation.Canvas(
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            drawRoundRect(
                                                color = color.copy(alpha = 0.15f),
                                                cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx())
                                            )
                                            drawRoundRect(
                                                color = color,
                                                size = size.copy(width = size.width * fraction),
                                                cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx())
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Top products
        item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        text = "Top 5 productos",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    if (stats.topProductos.isEmpty()) {
                        Text(
                            text = "Sin datos",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        stats.topProductos.forEachIndexed { index, prod ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "${index + 1}.",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = prod.producto,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "${prod.cantidad}x comprado",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Text(
                                    text = "S/ ${formatter.format(prod.monto)}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Daily spending trend
        item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        text = "Gasto en el tiempo",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    if (stats.gastoDiario.isEmpty()) {
                        Text(
                            text = "Sin datos",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        // Simple bar chart using Canvas
                        val maxDaily = stats.gastoDiario.maxOfOrNull { it.monto } ?: 1.0
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .horizontalScroll(rememberScrollState())
                        ) {
                            stats.gastoDiario.forEach { dia ->
                                val fraction = (dia.monto / maxDaily).toFloat()
                                val barHeight = (100 * fraction).coerceAtLeast(4f)
                                Box(
                                    modifier = Modifier
                                        .width(12.dp)
                                        .height(barHeight.dp)
                                ) {
                                    androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                                        drawRoundRect(
                                            color = androidx.compose.ui.graphics.Color(0xFF818CF8),
                                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}
