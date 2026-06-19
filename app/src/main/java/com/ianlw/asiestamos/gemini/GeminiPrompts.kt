package com.ianlw.asiestamos.gemini

/**
 * Prompt templates for Gemini API calls.
 * Designed for informal Peruvian Spanish input.
 */
object GeminiPrompts {

    const val MODEL_FLASH = "gemini-2.0-flash"

    fun textPrompt(input: String): String = """
Eres un asistente financiero personal para un usuario peruano. El usuario registró uno o varios gastos con estas palabras: "$input".

Extrae TODOS los gastos mencionados. Si hay varios, devuélvelos como lista.
Normaliza los montos: "45 80" o "45,80" = 45.80. Si el monto suena ambiguo por espacio entre dos números (como "45 80"), interpreta como decimal.
"un sol cincuenta" = 1.50, "diez" = 10.00, "cuarenta y cinco con ochenta" = 45.80.
Si el usuario mencionó un establecimiento, tienda o lugar donde compró, extráelo como título del registro.

Devuelve ÚNICAMENTE este JSON:
{
  "titulo": "nombre del establecimiento o null si no se menciona",
  "tieneEstablecimiento": true o false,
  "gastos": [
    {
      "monto": número decimal,
      "producto": "nombre claro y conciso del producto o servicio",
      "categoria": "Alimentación|Transporte|Salud|Entretenimiento|Educación|Hogar|Ropa|Tecnología|Otros",
      "descripcion": "descripción natural en una frase"
    }
  ]
}

Si no puedes determinar el monto de un ítem, usa 0.0. Moneda asumida: PEN (soles peruanos).
    """.trimIndent()

    fun imagePrompt(): String = """
Analiza esta imagen. Puede ser una boleta, ticket, recibo, o foto de productos/servicios que representan gastos.

Si es una boleta o recibo de un establecimiento:
- Extrae el nombre del establecimiento como título
- Lista cada ítem comprado como un gasto separado
- Si no puedes leer todos los ítems, extrae al menos el total y el tipo de establecimiento como un solo gasto

Si es una foto de producto o escena (no una boleta):
- Infiere qué se está comprando o pagando
- Extrae como un solo gasto

Devuelve ÚNICAMENTE este JSON:
{
  "titulo": "nombre del establecimiento o null si no aplica",
  "tieneEstablecimiento": true o false,
  "gastos": [
    {
      "monto": número decimal,
      "producto": "nombre del producto o servicio",
      "categoria": "Alimentación|Transporte|Salud|Entretenimiento|Educación|Hogar|Ropa|Tecnología|Otros",
      "descripcion": "descripción en una frase"
    }
  ]
}
    """.trimIndent()
}
