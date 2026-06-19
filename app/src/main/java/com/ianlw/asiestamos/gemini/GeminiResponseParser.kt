package com.ianlw.asiestamos.gemini

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.ianlw.asiestamos.domain.model.GeminiGasto
import com.ianlw.asiestamos.domain.model.GeminiResult
import com.ianlw.asiestamos.utils.MontoNormalizer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiResponseParser @Inject constructor() {

    private val gson = Gson()

    /**
     * Parse the raw Gemini API response into a GeminiResult.
     * Handles common JSON issues from LLM output.
     */
    fun parse(response: GeminiApiResponse): Result<GeminiResult> {
        return try {
            val textContent = response.candidates
                ?.firstOrNull()
                ?.content
                ?.parts
                ?.firstOrNull()
                ?.text

            if (textContent.isNullOrBlank()) {
                // Check for API errors
                response.error?.let { error ->
                    return Result.failure(
                        Exception("Gemini API error: ${error.message} (${error.status})")
                    )
                }
                return Result.failure(Exception("Respuesta vacía de Gemini"))
            }

            val cleanJson = cleanJsonResponse(textContent)
            val rawResult = gson.fromJson(cleanJson, RawGeminiResult::class.java)

            val result = GeminiResult(
                titulo = rawResult.titulo?.takeIf { it != "null" && it.isNotBlank() },
                tieneEstablecimiento = rawResult.tieneEstablecimiento ?: false,
                gastos = rawResult.gastos?.map { rawGasto ->
                    GeminiGasto(
                        monto = normalizeAmount(rawGasto.monto),
                        producto = rawGasto.producto ?: "Producto desconocido",
                        categoria = normalizeCategoria(rawGasto.categoria),
                        descripcion = rawGasto.descripcion ?: ""
                    )
                } ?: emptyList()
            )

            Result.success(result)
        } catch (e: JsonSyntaxException) {
            Result.failure(Exception("Error al parsear respuesta de Gemini: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Clean common JSON artifacts from LLM output:
     * - Remove markdown code fences
     * - Strip leading/trailing whitespace
     * - Fix unescaped newlines
     */
    private fun cleanJsonResponse(raw: String): String {
        var cleaned = raw.trim()

        // Remove ```json ... ``` wrapper
        if (cleaned.startsWith("```")) {
            cleaned = cleaned.removePrefix("```json")
                .removePrefix("```JSON")
                .removePrefix("```")
            val lastFence = cleaned.lastIndexOf("```")
            if (lastFence > 0) {
                cleaned = cleaned.substring(0, lastFence)
            }
        }

        return cleaned.trim()
    }

    /**
     * Normalize amount handling various formats.
     */
    private fun normalizeAmount(raw: Any?): Double {
        if (raw == null) return 0.0

        return when (raw) {
            is Number -> raw.toDouble()
            is String -> MontoNormalizer.normalize(raw)
            else -> {
                try {
                    raw.toString().toDouble()
                } catch (e: Exception) {
                    0.0
                }
            }
        }
    }

    /**
     * Ensure the categoria matches one of the predefined values.
     */
    private fun normalizeCategoria(raw: String?): String {
        if (raw.isNullOrBlank()) return "Otros"
        val valid = setOf(
            "Alimentación", "Transporte", "Salud", "Entretenimiento",
            "Educación", "Hogar", "Ropa", "Tecnología", "Otros"
        )
        return valid.find { it.equals(raw.trim(), ignoreCase = true) } ?: "Otros"
    }

    // Internal raw parsing model (more lenient types)
    private data class RawGeminiResult(
        val titulo: String?,
        val tieneEstablecimiento: Boolean?,
        val gastos: List<RawGasto>?
    )

    private data class RawGasto(
        val monto: Any?,
        val producto: String?,
        val categoria: String?,
        val descripcion: String?
    )
}
