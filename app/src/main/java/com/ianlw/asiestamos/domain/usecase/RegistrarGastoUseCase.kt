package com.ianlw.asiestamos.domain.usecase

import com.ianlw.asiestamos.domain.model.Registro
import com.ianlw.asiestamos.domain.repository.GastoRepository
import javax.inject.Inject

class RegistrarGastoUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    suspend operator fun invoke(registro: Registro): Long {
        return repository.insertRegistroConGastos(registro)
    }
}
