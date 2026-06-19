package com.ianlw.asiestamos.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ianlw.asiestamos.presentation.theme.ThemeType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSheet(
    dineroInicial: Double,
    apiKey: String,
    onDineroInicialChange: (Double) -> Unit,
    onApiKeyChange: (String) -> Unit,
    onTemaChange: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var dineroText by remember { mutableStateOf(if (dineroInicial > 0) dineroInicial.toString() else "") }
    var apiKeyText by remember { mutableStateOf(apiKey) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Ajustes",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Dinero inicial
            OutlinedTextField(
                value = dineroText,
                onValueChange = { newVal ->
                    dineroText = newVal
                    newVal.toDoubleOrNull()?.let { onDineroInicialChange(it) }
                },
                label = { Text("Dinero inicial (presupuesto)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                prefix = { Text("S/ ") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // API Key
            OutlinedTextField(
                value = apiKeyText,
                onValueChange = { newVal ->
                    apiKeyText = newVal
                    onApiKeyChange(newVal)
                },
                label = { Text("API Key de Gemini") },
                placeholder = { Text("Ingresa tu API Key de Google AI Studio") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Theme selector
            Text(
                text = "Tema visual",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ThemeType.entries.toList()) { theme ->
                    FilterChip(
                        selected = false,
                        onClick = { onTemaChange(theme.name) },
                        label = { Text(theme.displayName) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
