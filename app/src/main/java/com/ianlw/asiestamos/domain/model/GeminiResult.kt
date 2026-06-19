package com.ianlw.asiestamos.domain.model

/**
 * Represents the parsed response from Gemini API.
 * Can contain multiple expenses from a single input.
 */
data class GeminiResult(
    val titulo: String?,
    val tieneEstablecimiento: Boolean = false,
    val gastos: List<GeminiGasto> = emptyList()
)

data class GeminiGasto(
    val monto: Double,
    val producto: String,
    val categoria: String,
    val descripcion: String
)
