package com.ianlw.asiestamos.data.repository

import com.ianlw.asiestamos.data.local.dao.RegistroDao
import com.ianlw.asiestamos.data.local.entity.GastoEntity
import com.ianlw.asiestamos.data.local.entity.RegistroEntity
import com.ianlw.asiestamos.domain.model.CategoriaConMonto
import com.ianlw.asiestamos.domain.model.Gasto
import com.ianlw.asiestamos.domain.model.GastoDiario
import com.ianlw.asiestamos.domain.model.ProductoConMonto
import com.ianlw.asiestamos.domain.model.Registro
import com.ianlw.asiestamos.domain.model.TipoEntrada
import com.ianlw.asiestamos.domain.repository.GastoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GastoRepositoryImpl @Inject constructor(
    private val dao: RegistroDao
) : GastoRepository {

    override suspend fun insertRegistroConGastos(registro: Registro): Long {
        val entity = registro.toEntity()
        val gastoEntities = registro.gastos.map { it.toEntity() }
        dao.insertRegistroConGastos(entity, gastoEntities)
        return 0L // Room handles the ID internally
    }

    override suspend fun updateRegistroConGastos(registro: Registro) {
        val entity = registro.toEntity()
        val gastoEntities = registro.gastos.map { it.toEntity(registro.id) }
        dao.updateRegistroConGastos(entity, gastoEntities)
    }

    override suspend fun deleteRegistro(id: Int) {
        dao.deleteRegistroById(id)
    }

    override fun getAllRegistros(): Flow<List<Registro>> {
        return dao.getAllRegistrosConGastos().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getRegistroById(id: Int): Flow<Registro?> {
        return dao.getRegistroConGastosById(id).map { it?.toDomain() }
    }

    override fun searchRegistros(query: String): Flow<List<Registro>> {
        return dao.searchRegistros(query).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getRegistrosByDateRange(start: Long, end: Long): Flow<List<Registro>> {
        return dao.getRegistrosByDateRange(start, end).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getTotalGastado(): Flow<Double> = dao.getTotalGastado()

    override fun getTotalGastadoEnRango(start: Long, end: Long): Flow<Double> =
        dao.getTotalGastadoEnRango(start, end)

    override fun getGastosPorCategoriaEnRango(
        start: Long,
        end: Long
    ): Flow<List<CategoriaConMonto>> {
        return dao.getGastosPorCategoriaEnRango(start, end).map { list ->
            list.map { CategoriaConMonto(it.categoria, it.total) }
        }
    }

    override fun getTopProductosEnRango(start: Long, end: Long): Flow<List<ProductoConMonto>> {
        return dao.getTopProductosEnRango(start, end).map { list ->
            list.map { ProductoConMonto(it.producto, it.total, it.cantidad) }
        }
    }

    override fun getGastoDiarioEnRango(start: Long, end: Long): Flow<List<GastoDiario>> {
        return dao.getGastoDiarioEnRango(start, end).map { list ->
            list.map { GastoDiario(it.dia, it.total) }
        }
    }

    // === MAPPERS ===

    private fun com.ianlw.asiestamos.data.local.relation.RegistroConGastos.toDomain(): Registro {
        return Registro(
            id = registro.id,
            titulo = registro.titulo,
            tieneEstablecimiento = registro.tieneEstablecimiento,
            tipoEntrada = TipoEntrada.fromValor(registro.tipoEntrada),
            inputOriginal = registro.inputOriginal,
            fotoPath = registro.fotoPath,
            latitud = registro.latitud,
            longitud = registro.longitud,
            nombreUbicacion = registro.nombreUbicacion,
            fecha = registro.fecha,
            gastos = gastos.map { it.toDomain() }
        )
    }

    private fun GastoEntity.toDomain(): Gasto {
        return Gasto(
            id = id,
            registroId = registroId,
            monto = monto,
            producto = producto,
            descripcionIA = descripcionIA,
            categoria = categoria
        )
    }

    private fun Registro.toEntity(): RegistroEntity {
        return RegistroEntity(
            id = id,
            titulo = titulo,
            tieneEstablecimiento = tieneEstablecimiento,
            tipoEntrada = tipoEntrada.valor,
            inputOriginal = inputOriginal,
            fotoPath = fotoPath,
            latitud = latitud,
            longitud = longitud,
            nombreUbicacion = nombreUbicacion,
            fecha = fecha
        )
    }

    private fun Gasto.toEntity(registroId: Int = 0): GastoEntity {
        return GastoEntity(
            id = id,
            registroId = registroId,
            monto = monto,
            producto = producto,
            descripcionIA = descripcionIA,
            categoria = categoria
        )
    }
}
