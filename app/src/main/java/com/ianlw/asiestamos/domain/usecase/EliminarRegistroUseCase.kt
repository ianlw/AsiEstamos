package com.ianlw.asiestamos.domain.usecase

import com.ianlw.asiestamos.domain.repository.GastoRepository
import javax.inject.Inject

class EliminarRegistroUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteRegistro(id)
    }
}
