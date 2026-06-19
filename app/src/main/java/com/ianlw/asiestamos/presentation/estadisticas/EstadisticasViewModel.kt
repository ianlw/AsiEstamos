package com.ianlw.asiestamos.presentation.estadisticas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianlw.asiestamos.data.preferences.UserPreferences
import com.ianlw.asiestamos.domain.model.EstadisticasData
import com.ianlw.asiestamos.domain.usecase.ObtenerEstadisticasUseCase
import com.ianlw.asiestamos.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

enum class FiltroTiempo(val label: String) {
    HOY("Hoy"),
    SEMANA("Esta semana"),
    MES("Este mes"),
    ANIO("Este año");

    fun toRange(): Pair<Long, Long> = when (this) {
        HOY -> DateUtils.todayRange()
        SEMANA -> DateUtils.thisWeekRange()
        MES -> DateUtils.thisMonthRange()
        ANIO -> DateUtils.thisYearRange()
    }
}

@HiltViewModel
class EstadisticasViewModel @Inject constructor(
    private val obtenerEstadisticas: ObtenerEstadisticasUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _filtro = MutableStateFlow(FiltroTiempo.MES)
    val filtro: StateFlow<FiltroTiempo> = _filtro.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val estadisticas: StateFlow<EstadisticasData> = combine(
        _filtro,
        userPreferences.dineroInicial
    ) { filtro, dinero -> Triple(filtro, dinero, filtro.toRange()) }
        .flatMapLatest { (_, dinero, range) ->
            obtenerEstadisticas(range.first, range.second, dinero)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EstadisticasData())

    fun setFiltro(filtro: FiltroTiempo) {
        _filtro.value = filtro
    }
}
