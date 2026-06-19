package com.ianlw.asiestamos.domain.usecase

import com.ianlw.asiestamos.domain.model.GeminiResult
import com.ianlw.asiestamos.domain.repository.GeminiRepository
import javax.inject.Inject

class ProcesarImagenConIAUseCase @Inject constructor(
    private val repository: GeminiRepository
) {
    suspend operator fun invoke(imageBytes: ByteArray, apiKey: String): Result<GeminiResult> {
        return repository.processImage(imageBytes, apiKey)
    }
}
