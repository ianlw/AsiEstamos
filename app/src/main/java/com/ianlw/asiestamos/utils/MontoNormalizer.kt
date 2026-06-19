package com.ianlw.asiestamos.utils

/**
 * Normalizes ambiguous amount formats common in informal
 * Peruvian Spanish speech and writing.
 *
 * Examples:
 * - "45 80" → 45.80
 * - "45,80" → 45.80
 * - "45.80" → 45.80
 * - "10" → 10.0
 * - "un sol cincuenta" → handled by Gemini, fallback here
 */
object MontoNormalizer {

    fun normalize(raw: String): Double {
        val trimmed = raw.trim()

        // Try direct parsing first
        trimmed.toDoubleOrNull()?.let { return it }

        // Replace comma with dot: "45,80" → "45.80"
        trimmed.replace(",", ".").toDoubleOrNull()?.let { return it }

        // Handle "45 80" pattern (two numbers separated by space, second ≤2 digits)
        val spaceParts = trimmed.split("\\s+".toRegex())
        if (spaceParts.size == 2) {
            val intPart = spaceParts[0].toIntOrNull()
            val decPart = spaceParts[1]
            if (intPart != null && decPart.length <= 2 && decPart.toIntOrNull() != null) {
                return "$intPart.$decPart".toDouble()
            }
        }

        // Remove currency symbols and try again
        val cleaned = trimmed
            .replace("S/", "")
            .replace("S/.", "")
            .replace("$", "")
            .replace("soles", "")
            .replace("sol", "")
            .trim()

        cleaned.toDoubleOrNull()?.let { return it }
        cleaned.replace(",", ".").toDoubleOrNull()?.let { return it }

        // Final fallback
        return 0.0
    }
}
