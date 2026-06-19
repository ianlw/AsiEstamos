package com.ianlw.asiestamos.presentation.historial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianlw.asiestamos.domain.model.Registro
import com.ianlw.asiestamos.domain.usecase.EliminarRegistroUseCase
import com.ianlw.asiestamos.domain.usecase.ObtenerHistorialUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HistorialUiState(
    val searchQuery: String = "",
    val selectedCategorias: Set<String> = emptySet(),
    val expandedRegistroId: Int? = null,
    val showDeleteConfirm: Int? = null // registro ID to confirm delete
)

@HiltViewModel
class HistorialViewModel @Inject constructor(
    private val obtenerHistorial: ObtenerHistorialUseCase,
    private val eliminarRegistro: EliminarRegistroUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistorialUiState())
    val uiState: StateFlow<HistorialUiState> = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val registros: StateFlow<List<Registro>> = _uiState
        .flatMapLatest { state ->
            if (state.searchQuery.isBlank()) {
                obtenerHistorial()
            } else {
                obtenerHistorial.search(state.searchQuery)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateSearch(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun toggleCategoria(categoria: String) {
        val current = _uiState.value.selectedCategorias.toMutableSet()
        if (current.contains(categoria)) current.remove(categoria) else current.add(categoria)
        _uiState.value = _uiState.value.copy(selectedCategorias = current)
    }

    fun toggleExpanded(registroId: Int) {
        val current = _uiState.value.expandedRegistroId
        _uiState.value = _uiState.value.copy(
            expandedRegistroId = if (current == registroId) null else registroId
        )
    }

    fun showDeleteConfirm(registroId: Int) {
        _uiState.value = _uiState.value.copy(showDeleteConfirm = registroId)
    }

    fun hideDeleteConfirm() {
        _uiState.value = _uiState.value.copy(showDeleteConfirm = null)
    }

    fun deleteRegistro(id: Int) {
        viewModelScope.launch {
            eliminarRegistro(id)
            hideDeleteConfirm()
        }
    }
}
