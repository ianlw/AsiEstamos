package com.ianlw.asiestamos.domain.usecase

import com.ianlw.asiestamos.domain.model.GeminiResult
import com.ianlw.asiestamos.domain.repository.GeminiRepository
import javax.inject.Inject

class ProcesarTextoConIAUseCase @Inject constructor(
    private val repository: GeminiRepository
) {
    suspend operator fun invoke(input: String, apiKey: String): Result<GeminiResult> {
        return repository.processText(input, apiKey)
    }
}
