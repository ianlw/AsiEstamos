package com.ianlw.asiestamos.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ianlw.asiestamos.domain.model.Categoria
import com.ianlw.asiestamos.domain.model.Gasto
import com.ianlw.asiestamos.presentation.components.CategoryChip
import com.ianlw.asiestamos.presentation.components.ShimmerLoading
import com.ianlw.asiestamos.utils.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmacionSheet(
    isLoading: Boolean,
    titulo: String,
    gastos: List<Gasto>,
    fotoPath: String?,
    location: String?,
    errorMessage: String?,
    simboloMoneda: String,
    onTituloChange: (String) -> Unit,
    onGastoChange: (Int, Gasto) -> Unit,
    onRemoveGasto: (Int) -> Unit,
    onAddGasto: () -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            if (isLoading) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Procesando con IA...",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    ShimmerLoading()
                    Spacer(modifier = Modifier.height(32.dp))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Header
                    item {
                        Text(
                            text = "Confirmar gastos",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Error message
                    if (errorMessage != null) {
                        item {
                            Text(
                                text = errorMessage,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    // Title field
                    item {
                        OutlinedTextField(
                            value = titulo,
                            onValueChange = onTituloChange,
                            label = { Text("Título del registro") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    // Meta info
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = DateUtils.formatDateTime(System.currentTimeMillis()),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (location != null) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.LocationOn,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp),
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(
                                        text = location,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1
                                    )
                                }
                            }
                            if (fotoPath != null) {
                                Icon(
                                    Icons.Default.Photo,
                                    contentDescription = "Foto adjunta",
                                    modifier = Modifier.size(14.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    item {
                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                    }

                    // Expense items
                    itemsIndexed(gastos) { index, gasto ->
                        GastoEditItem(
                            gasto = gasto,
                            index = index,
                            simboloMoneda = simboloMoneda,
                            onGastoChange = { onGastoChange(index, it) },
                            onRemove = { onRemoveGasto(index) }
                        )
                        if (index < gastos.lastIndex) {
                            Divider(
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f),
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }

                    // Add item button
                    item {
                        TextButton(
                            onClick = onAddGasto,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Agregar ítem")
                        }
                    }

                    // Action buttons
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = onDismiss,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Cancelar")
                            }
                            Button(
                                onClick = onSave,
                                modifier = Modifier.weight(1f),
                                enabled = gastos.isNotEmpty()
                            ) {
                                Text("Guardar todo")
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun GastoEditItem(
    gasto: Gasto,
    index: Int,
    simboloMoneda: String,
    onGastoChange: (Gasto) -> Unit,
    onRemove: () -> Unit
) {
    var montoText by remember(gasto.monto) {
        mutableStateOf(if (gasto.monto > 0) gasto.monto.toString() else "")
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Amount
                OutlinedTextField(
                    value = montoText,
                    onValueChange = { newValue ->
                        montoText = newValue
                        val amount = newValue.toDoubleOrNull() ?: 0.0
                        onGastoChange(gasto.copy(monto = amount))
                    },
                    label = { Text("$simboloMoneda Monto") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Product
                OutlinedTextField(
                    value = gasto.producto,
                    onValueChange = { onGastoChange(gasto.copy(producto = it)) },
                    label = { Text("Producto") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            IconButton(onClick = onRemove) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Category chips
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Categoria.all.take(5).forEach { cat ->
                CategoryChip(
                    categoria = cat.valor,
                    selected = gasto.categoria == cat.valor,
                    onClick = { onGastoChange(gasto.copy(categoria = cat.valor)) }
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Categoria.all.drop(5).forEach { cat ->
                CategoryChip(
                    categoria = cat.valor,
                    selected = gasto.categoria == cat.valor,
                    onClick = { onGastoChange(gasto.copy(categoria = cat.valor)) }
                )
            }
        }
    }
}
