package com.ianlw.asiestamos.data.repository

import android.util.Base64
import com.ianlw.asiestamos.domain.model.GeminiResult
import com.ianlw.asiestamos.domain.repository.GeminiRepository
import com.ianlw.asiestamos.gemini.GeminiApiService
import com.ianlw.asiestamos.gemini.GeminiContent
import com.ianlw.asiestamos.gemini.GeminiPart
import com.ianlw.asiestamos.gemini.GeminiPrompts
import com.ianlw.asiestamos.gemini.GeminiRequest
import com.ianlw.asiestamos.gemini.GeminiResponseParser
import com.ianlw.asiestamos.gemini.InlineData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepositoryImpl @Inject constructor(
    private val apiService: GeminiApiService,
    private val parser: GeminiResponseParser
) : GeminiRepository {

    override suspend fun processText(input: String, apiKey: String): Result<GeminiResult> {
        return try {
            val request = GeminiRequest(
                contents = listOf(
                    GeminiContent(
                        parts = listOf(
                            GeminiPart(text = GeminiPrompts.textPrompt(input))
                        )
                    )
                )
            )

            val response = apiService.generateContent(
                model = GeminiPrompts.MODEL_FLASH,
                apiKey = apiKey,
                request = request
            )

            parser.parse(response)
        } catch (e: Exception) {
            Result.failure(Exception("Error al conectar con Gemini: ${e.message}"))
        }
    }

    override suspend fun processImage(imageBytes: ByteArray, apiKey: String): Result<GeminiResult> {
        return try {
            val base64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP)

            val request = GeminiRequest(
                contents = listOf(
                    GeminiContent(
                        parts = listOf(
                            GeminiPart(text = GeminiPrompts.imagePrompt()),
                            GeminiPart(
                                inlineData = InlineData(
                                    mimeType = "image/jpeg",
                                    data = base64Image
                                )
                            )
                        )
                    )
                )
            )

            val response = apiService.generateContent(
                model = GeminiPrompts.MODEL_FLASH,
                apiKey = apiKey,
                request = request
            )

            parser.parse(response)
        } catch (e: Exception) {
            Result.failure(Exception("Error al procesar imagen con Gemini: ${e.message}"))
        }
    }
}
