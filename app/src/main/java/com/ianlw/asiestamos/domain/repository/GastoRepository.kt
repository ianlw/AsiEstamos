package com.ianlw.asiestamos.domain.repository

import com.ianlw.asiestamos.domain.model.CategoriaConMonto
import com.ianlw.asiestamos.domain.model.GastoDiario
import com.ianlw.asiestamos.domain.model.ProductoConMonto
import com.ianlw.asiestamos.domain.model.Registro
import kotlinx.coroutines.flow.Flow

interface GastoRepository {
    // CRUD
    suspend fun insertRegistroConGastos(registro: Registro): Long
    suspend fun updateRegistroConGastos(registro: Registro)
    suspend fun deleteRegistro(id: Int)

    // Queries
    fun getAllRegistros(): Flow<List<Registro>>
    fun getRegistroById(id: Int): Flow<Registro?>
    fun searchRegistros(query: String): Flow<List<Registro>>
    fun getRegistrosByDateRange(start: Long, end: Long): Flow<List<Registro>>

    // Statistics
    fun getTotalGastado(): Flow<Double>
    fun getTotalGastadoEnRango(start: Long, end: Long): Flow<Double>
    fun getGastosPorCategoriaEnRango(start: Long, end: Long): Flow<List<CategoriaConMonto>>
    fun getTopProductosEnRango(start: Long, end: Long): Flow<List<ProductoConMonto>>
    fun getGastoDiarioEnRango(start: Long, end: Long): Flow<List<GastoDiario>>
}
