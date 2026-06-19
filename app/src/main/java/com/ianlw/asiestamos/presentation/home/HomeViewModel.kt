package com.ianlw.asiestamos.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianlw.asiestamos.data.preferences.UserPreferences
import com.ianlw.asiestamos.domain.model.Gasto
import com.ianlw.asiestamos.domain.model.GeminiResult
import com.ianlw.asiestamos.domain.model.Registro
import com.ianlw.asiestamos.domain.model.TipoEntrada
import com.ianlw.asiestamos.domain.repository.GastoRepository
import com.ianlw.asiestamos.domain.usecase.ProcesarImagenConIAUseCase
import com.ianlw.asiestamos.domain.usecase.ProcesarTextoConIAUseCase
import com.ianlw.asiestamos.domain.usecase.RegistrarGastoUseCase
import com.ianlw.asiestamos.utils.LocationData
import com.ianlw.asiestamos.utils.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val dineroInicial: Double = 0.0,
    val totalGastado: Double = 0.0,
    val isProcessing: Boolean = false,
    val geminiResult: GeminiResult? = null,
    val editableGastos: List<Gasto> = emptyList(),
    val editableTitulo: String = "",
    val tieneEstablecimiento: Boolean = false,
    val showConfirmation: Boolean = false,
    val showTextInput: Boolean = false,
    val showVoiceInput: Boolean = false,
    val showCamera: Boolean = false,
    val showSettings: Boolean = false,
    val currentLocation: LocationData? = null,
    val currentFotoPath: String? = null,
    val currentTipoEntrada: TipoEntrada = TipoEntrada.TEXTO,
    val currentInputOriginal: String = "",
    val errorMessage: String? = null,
    val apiKey: String = "",
    val simboloMoneda: String = "S/"
) {
    val balance: Double get() = dineroInicial - totalGastado
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val registrarGastoUseCase: RegistrarGastoUseCase,
    private val procesarTextoConIA: ProcesarTextoConIAUseCase,
    private val procesarImagenConIA: ProcesarImagenConIAUseCase,
    private val gastoRepository: GastoRepository,
    private val userPreferences: UserPreferences,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                userPreferences.dineroInicial,
                userPreferences.geminiApiKey,
                userPreferences.simboloMoneda,
                gastoRepository.getTotalGastado()
            ) { dinero, key, simbolo, total ->
                _uiState.value.copy(
                    dineroInicial = dinero,
                    apiKey = key,
                    simboloMoneda = simbolo,
                    totalGastado = total
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
        // Fetch location in background
        viewModelScope.launch {
            val loc = LocationHelper.getCurrentLocation(context)
            _uiState.value = _uiState.value.copy(currentLocation = loc)
        }
    }

    // === INPUT TOGGLES ===
    fun showTextInput() { _uiState.value = _uiState.value.copy(showTextInput = true) }
    fun hideTextInput() { _uiState.value = _uiState.value.copy(showTextInput = false) }
    fun showVoiceInput() { _uiState.value = _uiState.value.copy(showVoiceInput = true) }
    fun hideVoiceInput() { _uiState.value = _uiState.value.copy(showVoiceInput = false) }
    fun showCamera() { _uiState.value = _uiState.value.copy(showCamera = true) }
    fun hideCamera() { _uiState.value = _uiState.value.copy(showCamera = false) }
    fun showSettings() { _uiState.value = _uiState.value.copy(showSettings = true) }
    fun hideSettings() { _uiState.value = _uiState.value.copy(showSettings = false) }

    // === PROCESS TEXT (keyboard or voice transcription) ===
    fun processText(input: String, tipoEntrada: TipoEntrada = TipoEntrada.TEXTO) {
        val apiKey = _uiState.value.apiKey
        if (apiKey.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Configura tu API Key de Gemini en Ajustes")
            return
        }
        _uiState.value = _uiState.value.copy(
            isProcessing = true,
            showConfirmation = true,
            showTextInput = false,
            showVoiceInput = false,
            currentTipoEntrada = tipoEntrada,
            currentInputOriginal = input,
            errorMessage = null
        )
        viewModelScope.launch {
            val result = procesarTextoConIA(input, apiKey)
            result.fold(
                onSuccess = { geminiResult -> applyGeminiResult(geminiResult) },
                onFailure = { error -> showManualEntry(error.message) }
            )
        }
    }

    // === PROCESS IMAGE ===
    fun processImage(imageBytes: ByteArray, fotoPath: String?) {
        val apiKey = _uiState.value.apiKey
        if (apiKey.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Configura tu API Key de Gemini en Ajustes")
            return
        }
        _uiState.value = _uiState.value.copy(
            isProcessing = true,
            showConfirmation = true,
            showCamera = false,
            currentTipoEntrada = TipoEntrada.FOTO,
            currentInputOriginal = "Imagen adjunta",
            currentFotoPath = fotoPath,
            errorMessage = null
        )
        viewModelScope.launch {
            val result = procesarImagenConIA(imageBytes, apiKey)
            result.fold(
                onSuccess = { geminiResult -> applyGeminiResult(geminiResult) },
                onFailure = { error -> showManualEntry(error.message) }
            )
        }
    }

    private fun applyGeminiResult(result: GeminiResult) {
        val gastos = result.gastos.mapIndexed { index, g ->
            Gasto(
                id = index,
                monto = g.monto,
                producto = g.producto,
                descripcionIA = g.descripcion,
                categoria = g.categoria
            )
        }
        _uiState.value = _uiState.value.copy(
            isProcessing = false,
            geminiResult = result,
            editableGastos = gastos,
            editableTitulo = result.titulo ?: "Varios gastos",
            tieneEstablecimiento = result.tieneEstablecimiento
        )
    }

    private fun showManualEntry(errorMsg: String?) {
        _uiState.value = _uiState.value.copy(
            isProcessing = false,
            errorMessage = errorMsg ?: "Error al procesar con IA",
            editableGastos = listOf(Gasto()),
            editableTitulo = "Nuevo gasto"
        )
    }

    // === EDITING ===
    fun updateGasto(index: Int, gasto: Gasto) {
        val list = _uiState.value.editableGastos.toMutableList()
        if (index in list.indices) {
            list[index] = gasto
            _uiState.value = _uiState.value.copy(editableGastos = list)
        }
    }

    fun removeGasto(index: Int) {
        val list = _uiState.value.editableGastos.toMutableList()
        if (index in list.indices) {
            list.removeAt(index)
            _uiState.value = _uiState.value.copy(editableGastos = list)
        }
    }

    fun addEmptyGasto() {
        val list = _uiState.value.editableGastos + Gasto()
        _uiState.value = _uiState.value.copy(editableGastos = list)
    }

    fun updateTitulo(titulo: String) {
        _uiState.value = _uiState.value.copy(editableTitulo = titulo)
    }

    // === SAVE ===
    fun saveRegistro() {
        val state = _uiState.value
        val registro = Registro(
            titulo = state.editableTitulo,
            tieneEstablecimiento = state.tieneEstablecimiento,
            tipoEntrada = state.currentTipoEntrada,
            inputOriginal = state.currentInputOriginal,
            fotoPath = state.currentFotoPath,
            latitud = state.currentLocation?.latitude,
            longitud = state.currentLocation?.longitude,
            nombreUbicacion = state.currentLocation?.locationName,
            fecha = System.currentTimeMillis(),
            gastos = state.editableGastos
        )
        viewModelScope.launch {
            registrarGastoUseCase(registro)
            dismissConfirmation()
        }
    }

    fun dismissConfirmation() {
        _uiState.value = _uiState.value.copy(
            showConfirmation = false,
            geminiResult = null,
            editableGastos = emptyList(),
            editableTitulo = "",
            currentFotoPath = null,
            currentInputOriginal = "",
            errorMessage = null
        )
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    // === SETTINGS ===
    fun updateDineroInicial(monto: Double) {
        viewModelScope.launch { userPreferences.setDineroInicial(monto) }
    }

    fun updateApiKey(key: String) {
        viewModelScope.launch { userPreferences.setGeminiApiKey(key) }
    }

    fun updateTema(tema: String) {
        viewModelScope.launch { userPreferences.setTemaSeleccionado(tema) }
    }
}
