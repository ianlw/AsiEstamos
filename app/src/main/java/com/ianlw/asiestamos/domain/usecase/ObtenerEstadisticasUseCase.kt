package com.ianlw.asiestamos.domain.usecase

import com.ianlw.asiestamos.domain.model.EstadisticasData
import com.ianlw.asiestamos.domain.repository.GastoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ObtenerEstadisticasUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    operator fun invoke(start: Long, end: Long, dineroInicial: Double): Flow<EstadisticasData> {
        return combine(
            repository.getTotalGastadoEnRango(start, end),
            repository.getGastosPorCategoriaEnRango(start, end),
            repository.getTopProductosEnRango(start, end),
            repository.getGastoDiarioEnRango(start, end)
        ) { total, categorias, productos, diario ->
            val dias = if (diario.isNotEmpty()) diario.size.coerceAtLeast(1) else 1
            EstadisticasData(
                totalGastado = total,
                categoriaTop = categorias.firstOrNull(),
                promedioDiario = total / dias,
                porcentajePresupuesto = if (dineroInicial > 0) (total / dineroInicial) * 100 else 0.0,
                gastosPorCategoria = categorias,
                gastoDiario = diario,
                topProductos = productos
            )
        }
    }
}
