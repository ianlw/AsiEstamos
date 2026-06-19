package com.ianlw.asiestamos.gemini

import com.google.gson.annotations.SerializedName

// === REQUEST MODELS ===

data class GeminiRequest(
    val contents: List<GeminiContent>,
    val generationConfig: GenerationConfig? = GenerationConfig()
)

data class GeminiContent(
    val parts: List<GeminiPart>,
    val role: String = "user"
)

/**
 * A part can be text or an inline image (base64).
 * Only one of text or inlineData should be non-null.
 */
data class GeminiPart(
    val text: String? = null,
    @SerializedName("inline_data")
    val inlineData: InlineData? = null
)

data class InlineData(
    @SerializedName("mime_type")
    val mimeType: String,
    val data: String // base64-encoded image
)

data class GenerationConfig(
    val temperature: Float = 0.2f,
    val topP: Float = 0.8f,
    val maxOutputTokens: Int = 2048,
    @SerializedName("response_mime_type")
    val responseMimeType: String = "application/json"
)

// === RESPONSE MODELS ===

data class GeminiApiResponse(
    val candidates: List<GeminiCandidate>? = null,
    val error: GeminiError? = null
)

data class GeminiCandidate(
    val content: GeminiContent? = null,
    val finishReason: String? = null
)

data class GeminiError(
    val code: Int? = null,
    val message: String? = null,
    val status: String? = null
)
