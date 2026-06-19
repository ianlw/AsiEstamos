package com.ianlw.asiestamos.domain.model

/**
 * Data models for the statistics/dashboard screen.
 */
data class EstadisticasData(
    val totalGastado: Double = 0.0,
    val categoriaTop: CategoriaConMonto? = null,
    val promedioDiario: Double = 0.0,
    val porcentajePresupuesto: Double = 0.0,
    val gastosPorCategoria: List<CategoriaConMonto> = emptyList(),
    val gastoDiario: List<GastoDiario> = emptyList(),
    val topProductos: List<ProductoConMonto> = emptyList()
)

data class CategoriaConMonto(
    val categoria: String,
    val monto: Double
)

data class GastoDiario(
    val fecha: String, // "2024-01-15"
    val monto: Double
)

data class ProductoConMonto(
    val producto: String,
    val monto: Double,
    val cantidad: Int = 0
)
