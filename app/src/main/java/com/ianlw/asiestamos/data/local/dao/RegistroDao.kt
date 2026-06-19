package com.ianlw.asiestamos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ianlw.asiestamos.data.local.entity.GastoEntity
import com.ianlw.asiestamos.data.local.entity.RegistroEntity
import com.ianlw.asiestamos.data.local.relation.RegistroConGastos
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroDao {

    // === INSERT ===

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegistro(registro: RegistroEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGastos(gastos: List<GastoEntity>)

    @Transaction
    suspend fun insertRegistroConGastos(registro: RegistroEntity, gastos: List<GastoEntity>) {
        val registroId = insertRegistro(registro)
        val gastosConId = gastos.map { it.copy(registroId = registroId.toInt()) }
        insertGastos(gastosConId)
    }

    // === READ ===

    @Transaction
    @Query("SELECT * FROM registros ORDER BY fecha DESC")
    fun getAllRegistrosConGastos(): Flow<List<RegistroConGastos>>

    @Transaction
    @Query("SELECT * FROM registros WHERE id = :id")
    fun getRegistroConGastosById(id: Int): Flow<RegistroConGastos?>

    @Transaction
    @Query("""
        SELECT * FROM registros 
        WHERE titulo LIKE '%' || :query || '%' 
        OR inputOriginal LIKE '%' || :query || '%'
        OR id IN (
            SELECT DISTINCT registroId FROM gastos 
            WHERE producto LIKE '%' || :query || '%' 
            OR descripcionIA LIKE '%' || :query || '%'
        )
        ORDER BY fecha DESC
    """)
    fun searchRegistros(query: String): Flow<List<RegistroConGastos>>

    @Transaction
    @Query("SELECT * FROM registros WHERE fecha BETWEEN :start AND :end ORDER BY fecha DESC")
    fun getRegistrosByDateRange(start: Long, end: Long): Flow<List<RegistroConGastos>>

    // === STATISTICS QUERIES (on gastos table) ===

    @Query("SELECT COALESCE(SUM(monto), 0.0) FROM gastos")
    fun getTotalGastado(): Flow<Double>

    @Query("""
        SELECT COALESCE(SUM(g.monto), 0.0) FROM gastos g 
        INNER JOIN registros r ON g.registroId = r.id 
        WHERE r.fecha BETWEEN :start AND :end
    """)
    fun getTotalGastadoEnRango(start: Long, end: Long): Flow<Double>

    @Query("""
        SELECT g.categoria, SUM(g.monto) as total 
        FROM gastos g 
        INNER JOIN registros r ON g.registroId = r.id 
        WHERE r.fecha BETWEEN :start AND :end 
        GROUP BY g.categoria 
        ORDER BY total DESC
    """)
    fun getGastosPorCategoriaEnRango(start: Long, end: Long): Flow<List<CategoriaTotal>>

    @Query("""
        SELECT g.producto, SUM(g.monto) as total, COUNT(*) as cantidad
        FROM gastos g 
        INNER JOIN registros r ON g.registroId = r.id 
        WHERE r.fecha BETWEEN :start AND :end 
        GROUP BY g.producto 
        ORDER BY total DESC 
        LIMIT 5
    """)
    fun getTopProductosEnRango(start: Long, end: Long): Flow<List<ProductoTotal>>

    @Query("""
        SELECT DATE(r.fecha / 1000, 'unixepoch', 'localtime') as dia, 
               SUM(g.monto) as total
        FROM gastos g 
        INNER JOIN registros r ON g.registroId = r.id 
        WHERE r.fecha BETWEEN :start AND :end 
        GROUP BY dia 
        ORDER BY dia ASC
    """)
    fun getGastoDiarioEnRango(start: Long, end: Long): Flow<List<DiaTotal>>

    @Query("""
        SELECT DISTINCT g.categoria FROM gastos g
        INNER JOIN registros r ON g.registroId = r.id
        WHERE r.id = :registroId
    """)
    fun getCategoriasDeRegistro(registroId: Int): Flow<List<String>>

    // === UPDATE ===

    @Update
    suspend fun updateRegistro(registro: RegistroEntity)

    @Update
    suspend fun updateGasto(gasto: GastoEntity)

    @Transaction
    suspend fun updateRegistroConGastos(registro: RegistroEntity, gastos: List<GastoEntity>) {
        updateRegistro(registro)
        // Delete existing gastos and re-insert
        deleteGastosByRegistroId(registro.id)
        insertGastos(gastos.map { it.copy(registroId = registro.id) })
    }

    // === DELETE ===

    @Query("DELETE FROM registros WHERE id = :id")
    suspend fun deleteRegistroById(id: Int)

    @Query("DELETE FROM gastos WHERE registroId = :registroId")
    suspend fun deleteGastosByRegistroId(registroId: Int)
}

// Helper data classes for query results
data class CategoriaTotal(
    val categoria: String,
    val total: Double
)

data class ProductoTotal(
    val producto: String,
    val total: Double,
    val cantidad: Int
)

data class DiaTotal(
    val dia: String,
    val total: Double
)
