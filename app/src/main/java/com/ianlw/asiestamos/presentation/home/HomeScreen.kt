package com.ianlw.asiestamos.presentation.home

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ianlw.asiestamos.domain.model.TipoEntrada
import com.ianlw.asiestamos.presentation.components.AnimatedBalance
import com.ianlw.asiestamos.presentation.components.GlassCard
import com.ianlw.asiestamos.utils.HapticHelper
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var showButtons by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        showButtons = true
    }

    // Voice recognition launcher
    val voiceLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val text = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
            text?.let { viewModel.processText(it, TipoEntrada.VOZ) }
        }
    }

    // Photo picker launcher
    val photoPickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val bytes = com.ianlw.asiestamos.utils.PhotoHelper.compressForGemini(context, it)
            val path = it.toString()
            bytes?.let { b -> viewModel.processImage(b, path) }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top bar with settings
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Así Estamos",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                IconButton(onClick = { viewModel.showSettings() }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Balance card
            GlassCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Balance disponible",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimatedBalance(
                        amount = state.balance,
                        prefix = "${state.simboloMoneda} ",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = if (state.balance >= 0) {
                            MaterialTheme.colorScheme.onBackground
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    val formatter = remember {
                        NumberFormat.getNumberInstance(Locale("es", "PE")).apply {
                            minimumFractionDigits = 2
                            maximumFractionDigits = 2
                        }
                    }
                    Text(
                        text = "Total gastado: ${state.simboloMoneda} ${formatter.format(state.totalGastado)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                // Keyboard button
                AnimatedVisibility(
                    visible = showButtons,
                    enter = scaleIn(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                    exit = scaleOut()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FloatingActionButton(
                            onClick = {
                                HapticHelper.lightTap(context)
                                viewModel.showTextInput()
                            },
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            shape = CircleShape,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(Icons.Default.Keyboard, contentDescription = "Teclado", modifier = Modifier.size(28.dp))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Teclado", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                // Microphone button
                AnimatedVisibility(
                    visible = showButtons,
                    enter = scaleIn(
                        initialScale = 0.3f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                    exit = scaleOut()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FloatingActionButton(
                            onClick = {
                                HapticHelper.lightTap(context)
                                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                                    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-PE")
                                    putExtra(RecognizerIntent.EXTRA_PROMPT, "Dime lo que gastaste...")
                                }
                                voiceLauncher.launch(intent)
                            },
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            shape = CircleShape,
                            modifier = Modifier.size(80.dp) // Larger center button
                        ) {
                            Icon(Icons.Default.Mic, contentDescription = "Micrófono", modifier = Modifier.size(36.dp))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Voz", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                // Camera button
                AnimatedVisibility(
                    visible = showButtons,
                    enter = scaleIn(
                        initialScale = 0.3f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                    exit = scaleOut()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FloatingActionButton(
                            onClick = {
                                HapticHelper.lightTap(context)
                                photoPickerLauncher.launch("image/*")
                            },
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            shape = CircleShape,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(Icons.Default.CameraAlt, contentDescription = "Cámara", modifier = Modifier.size(28.dp))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Foto", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }

        // Text input bottom sheet
        if (state.showTextInput) {
            TextInputSheet(
                onDismiss = { viewModel.hideTextInput() },
                onSubmit = { text -> viewModel.processText(text) }
            )
        }

        // Confirmation bottom sheet
        if (state.showConfirmation) {
            ConfirmacionSheet(
                isLoading = state.isProcessing,
                titulo = state.editableTitulo,
                gastos = state.editableGastos,
                fotoPath = state.currentFotoPath,
                location = state.currentLocation?.locationName,
                errorMessage = state.errorMessage,
                simboloMoneda = state.simboloMoneda,
                onTituloChange = { viewModel.updateTitulo(it) },
                onGastoChange = { index, gasto -> viewModel.updateGasto(index, gasto) },
                onRemoveGasto = { viewModel.removeGasto(it) },
                onAddGasto = { viewModel.addEmptyGasto() },
                onSave = {
                    HapticHelper.success(context)
                    viewModel.saveRegistro()
                },
                onDismiss = { viewModel.dismissConfirmation() }
            )
        }

        // Settings bottom sheet
        if (state.showSettings) {
            SettingsSheet(
                dineroInicial = state.dineroInicial,
                apiKey = state.apiKey,
                onDineroInicialChange = { viewModel.updateDineroInicial(it) },
                onApiKeyChange = { viewModel.updateApiKey(it) },
                onTemaChange = { viewModel.updateTema(it) },
                onDismiss = { viewModel.hideSettings() }
            )
        }
    }
}
