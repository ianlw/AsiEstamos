package com.ianlw.asiestamos.domain.model

data class Gasto(
    val id: Int = 0,
    val registroId: Int = 0,
    val monto: Double = 0.0,
    val producto: String = "",
    val descripcionIA: String = "",
    val categoria: String = Categoria.OTROS.valor
)

enum class Categoria(val valor: String, val emoji: String) {
    ALIMENTACION("Alimentación", "🍽️"),
    TRANSPORTE("Transporte", "🚗"),
    SALUD("Salud", "🏥"),
    ENTRETENIMIENTO("Entretenimiento", "🎬"),
    EDUCACION("Educación", "📚"),
    HOGAR("Hogar", "🏠"),
    ROPA("Ropa", "👕"),
    TECNOLOGIA("Tecnología", "💻"),
    OTROS("Otros", "📦");

    companion object {
        fun fromValor(valor: String): Categoria =
            entries.find { it.valor.equals(valor, ignoreCase = true) } ?: OTROS

        val all: List<Categoria> get() = entries.toList()
    }
}
