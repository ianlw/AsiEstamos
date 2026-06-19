package com.ianlw.asiestamos.domain.repository

import com.ianlw.asiestamos.domain.model.GeminiResult

interface GeminiRepository {
    /**
     * Process a text/voice input with Gemini and extract expenses.
     */
    suspend fun processText(input: String, apiKey: String): Result<GeminiResult>

    /**
     * Process an image (receipt/photo) with Gemini Vision and extract expenses.
     */
    suspend fun processImage(imageBytes: ByteArray, apiKey: String): Result<GeminiResult>
}
