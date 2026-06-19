package com.ianlw.asiestamos.domain.model

data class Registro(
    val id: Int = 0,
    val titulo: String,
    val tieneEstablecimiento: Boolean = false,
    val tipoEntrada: TipoEntrada = TipoEntrada.TEXTO,
    val inputOriginal: String = "",
    val fotoPath: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    val nombreUbicacion: String? = null,
    val fecha: Long = System.currentTimeMillis(),
    val gastos: List<Gasto> = emptyList()
) {
    val montoTotal: Double get() = gastos.sumOf { it.monto }
    val categorias: List<String> get() = gastos.map { it.categoria }.distinct()
}

enum class TipoEntrada(val valor: String) {
    TEXTO("texto"),
    VOZ("voz"),
    FOTO("foto");

    companion object {
        fun fromValor(valor: String): TipoEntrada =
            entries.find { it.valor == valor } ?: TEXTO
    }
}
