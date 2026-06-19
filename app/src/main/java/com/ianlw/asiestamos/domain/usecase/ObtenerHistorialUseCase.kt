package com.ianlw.asiestamos.domain.usecase

import com.ianlw.asiestamos.domain.model.Registro
import com.ianlw.asiestamos.domain.repository.GastoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObtenerHistorialUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    operator fun invoke(): Flow<List<Registro>> = repository.getAllRegistros()

    fun search(query: String): Flow<List<Registro>> = repository.searchRegistros(query)

    fun byDateRange(start: Long, end: Long): Flow<List<Registro>> =
        repository.getRegistrosByDateRange(start, end)

    fun byId(id: Int): Flow<Registro?> = repository.getRegistroById(id)
}
