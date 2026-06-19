package com.ianlw.asiestamos.`data`.local.dao

import androidx.collection.LongSparseArray
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchLongSparseArray
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import com.ianlw.asiestamos.`data`.local.entity.GastoEntity
import com.ianlw.asiestamos.`data`.local.entity.RegistroEntity
import com.ianlw.asiestamos.`data`.local.relation.RegistroConGastos
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RegistroDao_Impl(
  __db: RoomDatabase,
) : RegistroDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRegistroEntity: EntityInsertAdapter<RegistroEntity>

  private val __insertAdapterOfGastoEntity: EntityInsertAdapter<GastoEntity>

  private val __updateAdapterOfRegistroEntity: EntityDeleteOrUpdateAdapter<RegistroEntity>

  private val __updateAdapterOfGastoEntity: EntityDeleteOrUpdateAdapter<GastoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRegistroEntity = object : EntityInsertAdapter<RegistroEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `registros` (`id`,`titulo`,`tieneEstablecimiento`,`tipoEntrada`,`inputOriginal`,`fotoPath`,`latitud`,`longitud`,`nombreUbicacion`,`fecha`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RegistroEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.titulo)
        val _tmp: Int = if (entity.tieneEstablecimiento) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.tipoEntrada)
        statement.bindText(5, entity.inputOriginal)
        val _tmpFotoPath: String? = entity.fotoPath
        if (_tmpFotoPath == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpFotoPath)
        }
        val _tmpLatitud: Double? = entity.latitud
        if (_tmpLatitud == null) {
          statement.bindNull(7)
        } else {
          statement.bindDouble(7, _tmpLatitud)
        }
        val _tmpLongitud: Double? = entity.longitud
        if (_tmpLongitud == null) {
          statement.bindNull(8)
        } else {
          statement.bindDouble(8, _tmpLongitud)
        }
        val _tmpNombreUbicacion: String? = entity.nombreUbicacion
        if (_tmpNombreUbicacion == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpNombreUbicacion)
        }
        statement.bindLong(10, entity.fecha)
      }
    }
    this.__insertAdapterOfGastoEntity = object : EntityInsertAdapter<GastoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `gastos` (`id`,`registroId`,`monto`,`producto`,`descripcionIA`,`categoria`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GastoEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.registroId.toLong())
        statement.bindDouble(3, entity.monto)
        statement.bindText(4, entity.producto)
        statement.bindText(5, entity.descripcionIA)
        statement.bindText(6, entity.categoria)
      }
    }
    this.__updateAdapterOfRegistroEntity = object : EntityDeleteOrUpdateAdapter<RegistroEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `registros` SET `id` = ?,`titulo` = ?,`tieneEstablecimiento` = ?,`tipoEntrada` = ?,`inputOriginal` = ?,`fotoPath` = ?,`latitud` = ?,`longitud` = ?,`nombreUbicacion` = ?,`fecha` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RegistroEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.titulo)
        val _tmp: Int = if (entity.tieneEstablecimiento) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.tipoEntrada)
        statement.bindText(5, entity.inputOriginal)
        val _tmpFotoPath: String? = entity.fotoPath
        if (_tmpFotoPath == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpFotoPath)
        }
        val _tmpLatitud: Double? = entity.latitud
        if (_tmpLatitud == null) {
          statement.bindNull(7)
        } else {
          statement.bindDouble(7, _tmpLatitud)
        }
        val _tmpLongitud: Double? = entity.longitud
        if (_tmpLongitud == null) {
          statement.bindNull(8)
        } else {
          statement.bindDouble(8, _tmpLongitud)
        }
        val _tmpNombreUbicacion: String? = entity.nombreUbicacion
        if (_tmpNombreUbicacion == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpNombreUbicacion)
        }
        statement.bindLong(10, entity.fecha)
        statement.bindLong(11, entity.id.toLong())
      }
    }
    this.__updateAdapterOfGastoEntity = object : EntityDeleteOrUpdateAdapter<GastoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `gastos` SET `id` = ?,`registroId` = ?,`monto` = ?,`producto` = ?,`descripcionIA` = ?,`categoria` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: GastoEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.registroId.toLong())
        statement.bindDouble(3, entity.monto)
        statement.bindText(4, entity.producto)
        statement.bindText(5, entity.descripcionIA)
        statement.bindText(6, entity.categoria)
        statement.bindLong(7, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertRegistro(registro: RegistroEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfRegistroEntity.insertAndReturnId(_connection, registro)
    _result
  }

  public override suspend fun insertGastos(gastos: List<GastoEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfGastoEntity.insert(_connection, gastos)
  }

  public override suspend fun updateRegistro(registro: RegistroEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfRegistroEntity.handle(_connection, registro)
  }

  public override suspend fun updateGasto(gasto: GastoEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfGastoEntity.handle(_connection, gasto)
  }

  public override suspend fun insertRegistroConGastos(registro: RegistroEntity,
      gastos: List<GastoEntity>): Unit = performInTransactionSuspending(__db) {
    super@RegistroDao_Impl.insertRegistroConGastos(registro, gastos)
  }

  public override suspend fun updateRegistroConGastos(registro: RegistroEntity,
      gastos: List<GastoEntity>): Unit = performInTransactionSuspending(__db) {
    super@RegistroDao_Impl.updateRegistroConGastos(registro, gastos)
  }

  public override fun getAllRegistrosConGastos(): Flow<List<RegistroConGastos>> {
    val _sql: String = "SELECT * FROM registros ORDER BY fecha DESC"
    return createFlow(__db, true, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfTieneEstablecimiento: Int = getColumnIndexOrThrow(_stmt,
            "tieneEstablecimiento")
        val _columnIndexOfTipoEntrada: Int = getColumnIndexOrThrow(_stmt, "tipoEntrada")
        val _columnIndexOfInputOriginal: Int = getColumnIndexOrThrow(_stmt, "inputOriginal")
        val _columnIndexOfFotoPath: Int = getColumnIndexOrThrow(_stmt, "fotoPath")
        val _columnIndexOfLatitud: Int = getColumnIndexOrThrow(_stmt, "latitud")
        val _columnIndexOfLongitud: Int = getColumnIndexOrThrow(_stmt, "longitud")
        val _columnIndexOfNombreUbicacion: Int = getColumnIndexOrThrow(_stmt, "nombreUbicacion")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _collectionGastos: LongSparseArray<MutableList<GastoEntity>> =
            LongSparseArray<MutableList<GastoEntity>>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfId)
          if (!_collectionGastos.containsKey(_tmpKey)) {
            _collectionGastos.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipgastosAscomIanlwAsiestamosDataLocalEntityGastoEntity(_connection,
            _collectionGastos)
        val _result: MutableList<RegistroConGastos> = mutableListOf()
        while (_stmt.step()) {
          val _item: RegistroConGastos
          val _tmpRegistro: RegistroEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpTieneEstablecimiento: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfTieneEstablecimiento).toInt()
          _tmpTieneEstablecimiento = _tmp != 0
          val _tmpTipoEntrada: String
          _tmpTipoEntrada = _stmt.getText(_columnIndexOfTipoEntrada)
          val _tmpInputOriginal: String
          _tmpInputOriginal = _stmt.getText(_columnIndexOfInputOriginal)
          val _tmpFotoPath: String?
          if (_stmt.isNull(_columnIndexOfFotoPath)) {
            _tmpFotoPath = null
          } else {
            _tmpFotoPath = _stmt.getText(_columnIndexOfFotoPath)
          }
          val _tmpLatitud: Double?
          if (_stmt.isNull(_columnIndexOfLatitud)) {
            _tmpLatitud = null
          } else {
            _tmpLatitud = _stmt.getDouble(_columnIndexOfLatitud)
          }
          val _tmpLongitud: Double?
          if (_stmt.isNull(_columnIndexOfLongitud)) {
            _tmpLongitud = null
          } else {
            _tmpLongitud = _stmt.getDouble(_columnIndexOfLongitud)
          }
          val _tmpNombreUbicacion: String?
          if (_stmt.isNull(_columnIndexOfNombreUbicacion)) {
            _tmpNombreUbicacion = null
          } else {
            _tmpNombreUbicacion = _stmt.getText(_columnIndexOfNombreUbicacion)
          }
          val _tmpFecha: Long
          _tmpFecha = _stmt.getLong(_columnIndexOfFecha)
          _tmpRegistro =
              RegistroEntity(_tmpId,_tmpTitulo,_tmpTieneEstablecimiento,_tmpTipoEntrada,_tmpInputOriginal,_tmpFotoPath,_tmpLatitud,_tmpLongitud,_tmpNombreUbicacion,_tmpFecha)
          val _tmpGastosCollection: MutableList<GastoEntity>
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfId)
          _tmpGastosCollection = checkNotNull(_collectionGastos.get(_tmpKey_1))
          _item = RegistroConGastos(_tmpRegistro,_tmpGastosCollection)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRegistroConGastosById(id: Int): Flow<RegistroConGastos?> {
    val _sql: String = "SELECT * FROM registros WHERE id = ?"
    return createFlow(__db, true, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfTieneEstablecimiento: Int = getColumnIndexOrThrow(_stmt,
            "tieneEstablecimiento")
        val _columnIndexOfTipoEntrada: Int = getColumnIndexOrThrow(_stmt, "tipoEntrada")
        val _columnIndexOfInputOriginal: Int = getColumnIndexOrThrow(_stmt, "inputOriginal")
        val _columnIndexOfFotoPath: Int = getColumnIndexOrThrow(_stmt, "fotoPath")
        val _columnIndexOfLatitud: Int = getColumnIndexOrThrow(_stmt, "latitud")
        val _columnIndexOfLongitud: Int = getColumnIndexOrThrow(_stmt, "longitud")
        val _columnIndexOfNombreUbicacion: Int = getColumnIndexOrThrow(_stmt, "nombreUbicacion")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _collectionGastos: LongSparseArray<MutableList<GastoEntity>> =
            LongSparseArray<MutableList<GastoEntity>>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfId)
          if (!_collectionGastos.containsKey(_tmpKey)) {
            _collectionGastos.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipgastosAscomIanlwAsiestamosDataLocalEntityGastoEntity(_connection,
            _collectionGastos)
        val _result: RegistroConGastos?
        if (_stmt.step()) {
          val _tmpRegistro: RegistroEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpTieneEstablecimiento: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfTieneEstablecimiento).toInt()
          _tmpTieneEstablecimiento = _tmp != 0
          val _tmpTipoEntrada: String
          _tmpTipoEntrada = _stmt.getText(_columnIndexOfTipoEntrada)
          val _tmpInputOriginal: String
          _tmpInputOriginal = _stmt.getText(_columnIndexOfInputOriginal)
          val _tmpFotoPath: String?
          if (_stmt.isNull(_columnIndexOfFotoPath)) {
            _tmpFotoPath = null
          } else {
            _tmpFotoPath = _stmt.getText(_columnIndexOfFotoPath)
          }
          val _tmpLatitud: Double?
          if (_stmt.isNull(_columnIndexOfLatitud)) {
            _tmpLatitud = null
          } else {
            _tmpLatitud = _stmt.getDouble(_columnIndexOfLatitud)
          }
          val _tmpLongitud: Double?
          if (_stmt.isNull(_columnIndexOfLongitud)) {
            _tmpLongitud = null
          } else {
            _tmpLongitud = _stmt.getDouble(_columnIndexOfLongitud)
          }
          val _tmpNombreUbicacion: String?
          if (_stmt.isNull(_columnIndexOfNombreUbicacion)) {
            _tmpNombreUbicacion = null
          } else {
            _tmpNombreUbicacion = _stmt.getText(_columnIndexOfNombreUbicacion)
          }
          val _tmpFecha: Long
          _tmpFecha = _stmt.getLong(_columnIndexOfFecha)
          _tmpRegistro =
              RegistroEntity(_tmpId,_tmpTitulo,_tmpTieneEstablecimiento,_tmpTipoEntrada,_tmpInputOriginal,_tmpFotoPath,_tmpLatitud,_tmpLongitud,_tmpNombreUbicacion,_tmpFecha)
          val _tmpGastosCollection: MutableList<GastoEntity>
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfId)
          _tmpGastosCollection = checkNotNull(_collectionGastos.get(_tmpKey_1))
          _result = RegistroConGastos(_tmpRegistro,_tmpGastosCollection)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchRegistros(query: String): Flow<List<RegistroConGastos>> {
    val _sql: String = """
        |
        |        SELECT * FROM registros 
        |        WHERE titulo LIKE '%' || ? || '%' 
        |        OR inputOriginal LIKE '%' || ? || '%'
        |        OR id IN (
        |            SELECT DISTINCT registroId FROM gastos 
        |            WHERE producto LIKE '%' || ? || '%' 
        |            OR descripcionIA LIKE '%' || ? || '%'
        |        )
        |        ORDER BY fecha DESC
        |    
        """.trimMargin()
    return createFlow(__db, true, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindText(_argIndex, query)
        _argIndex = 4
        _stmt.bindText(_argIndex, query)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfTieneEstablecimiento: Int = getColumnIndexOrThrow(_stmt,
            "tieneEstablecimiento")
        val _columnIndexOfTipoEntrada: Int = getColumnIndexOrThrow(_stmt, "tipoEntrada")
        val _columnIndexOfInputOriginal: Int = getColumnIndexOrThrow(_stmt, "inputOriginal")
        val _columnIndexOfFotoPath: Int = getColumnIndexOrThrow(_stmt, "fotoPath")
        val _columnIndexOfLatitud: Int = getColumnIndexOrThrow(_stmt, "latitud")
        val _columnIndexOfLongitud: Int = getColumnIndexOrThrow(_stmt, "longitud")
        val _columnIndexOfNombreUbicacion: Int = getColumnIndexOrThrow(_stmt, "nombreUbicacion")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _collectionGastos: LongSparseArray<MutableList<GastoEntity>> =
            LongSparseArray<MutableList<GastoEntity>>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfId)
          if (!_collectionGastos.containsKey(_tmpKey)) {
            _collectionGastos.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipgastosAscomIanlwAsiestamosDataLocalEntityGastoEntity(_connection,
            _collectionGastos)
        val _result: MutableList<RegistroConGastos> = mutableListOf()
        while (_stmt.step()) {
          val _item: RegistroConGastos
          val _tmpRegistro: RegistroEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpTieneEstablecimiento: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfTieneEstablecimiento).toInt()
          _tmpTieneEstablecimiento = _tmp != 0
          val _tmpTipoEntrada: String
          _tmpTipoEntrada = _stmt.getText(_columnIndexOfTipoEntrada)
          val _tmpInputOriginal: String
          _tmpInputOriginal = _stmt.getText(_columnIndexOfInputOriginal)
          val _tmpFotoPath: String?
          if (_stmt.isNull(_columnIndexOfFotoPath)) {
            _tmpFotoPath = null
          } else {
            _tmpFotoPath = _stmt.getText(_columnIndexOfFotoPath)
          }
          val _tmpLatitud: Double?
          if (_stmt.isNull(_columnIndexOfLatitud)) {
            _tmpLatitud = null
          } else {
            _tmpLatitud = _stmt.getDouble(_columnIndexOfLatitud)
          }
          val _tmpLongitud: Double?
          if (_stmt.isNull(_columnIndexOfLongitud)) {
            _tmpLongitud = null
          } else {
            _tmpLongitud = _stmt.getDouble(_columnIndexOfLongitud)
          }
          val _tmpNombreUbicacion: String?
          if (_stmt.isNull(_columnIndexOfNombreUbicacion)) {
            _tmpNombreUbicacion = null
          } else {
            _tmpNombreUbicacion = _stmt.getText(_columnIndexOfNombreUbicacion)
          }
          val _tmpFecha: Long
          _tmpFecha = _stmt.getLong(_columnIndexOfFecha)
          _tmpRegistro =
              RegistroEntity(_tmpId,_tmpTitulo,_tmpTieneEstablecimiento,_tmpTipoEntrada,_tmpInputOriginal,_tmpFotoPath,_tmpLatitud,_tmpLongitud,_tmpNombreUbicacion,_tmpFecha)
          val _tmpGastosCollection: MutableList<GastoEntity>
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfId)
          _tmpGastosCollection = checkNotNull(_collectionGastos.get(_tmpKey_1))
          _item = RegistroConGastos(_tmpRegistro,_tmpGastosCollection)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRegistrosByDateRange(start: Long, end: Long):
      Flow<List<RegistroConGastos>> {
    val _sql: String = "SELECT * FROM registros WHERE fecha BETWEEN ? AND ? ORDER BY fecha DESC"
    return createFlow(__db, true, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfTieneEstablecimiento: Int = getColumnIndexOrThrow(_stmt,
            "tieneEstablecimiento")
        val _columnIndexOfTipoEntrada: Int = getColumnIndexOrThrow(_stmt, "tipoEntrada")
        val _columnIndexOfInputOriginal: Int = getColumnIndexOrThrow(_stmt, "inputOriginal")
        val _columnIndexOfFotoPath: Int = getColumnIndexOrThrow(_stmt, "fotoPath")
        val _columnIndexOfLatitud: Int = getColumnIndexOrThrow(_stmt, "latitud")
        val _columnIndexOfLongitud: Int = getColumnIndexOrThrow(_stmt, "longitud")
        val _columnIndexOfNombreUbicacion: Int = getColumnIndexOrThrow(_stmt, "nombreUbicacion")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _collectionGastos: LongSparseArray<MutableList<GastoEntity>> =
            LongSparseArray<MutableList<GastoEntity>>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfId)
          if (!_collectionGastos.containsKey(_tmpKey)) {
            _collectionGastos.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipgastosAscomIanlwAsiestamosDataLocalEntityGastoEntity(_connection,
            _collectionGastos)
        val _result: MutableList<RegistroConGastos> = mutableListOf()
        while (_stmt.step()) {
          val _item: RegistroConGastos
          val _tmpRegistro: RegistroEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpTieneEstablecimiento: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfTieneEstablecimiento).toInt()
          _tmpTieneEstablecimiento = _tmp != 0
          val _tmpTipoEntrada: String
          _tmpTipoEntrada = _stmt.getText(_columnIndexOfTipoEntrada)
          val _tmpInputOriginal: String
          _tmpInputOriginal = _stmt.getText(_columnIndexOfInputOriginal)
          val _tmpFotoPath: String?
          if (_stmt.isNull(_columnIndexOfFotoPath)) {
            _tmpFotoPath = null
          } else {
            _tmpFotoPath = _stmt.getText(_columnIndexOfFotoPath)
          }
          val _tmpLatitud: Double?
          if (_stmt.isNull(_columnIndexOfLatitud)) {
            _tmpLatitud = null
          } else {
            _tmpLatitud = _stmt.getDouble(_columnIndexOfLatitud)
          }
          val _tmpLongitud: Double?
          if (_stmt.isNull(_columnIndexOfLongitud)) {
            _tmpLongitud = null
          } else {
            _tmpLongitud = _stmt.getDouble(_columnIndexOfLongitud)
          }
          val _tmpNombreUbicacion: String?
          if (_stmt.isNull(_columnIndexOfNombreUbicacion)) {
            _tmpNombreUbicacion = null
          } else {
            _tmpNombreUbicacion = _stmt.getText(_columnIndexOfNombreUbicacion)
          }
          val _tmpFecha: Long
          _tmpFecha = _stmt.getLong(_columnIndexOfFecha)
          _tmpRegistro =
              RegistroEntity(_tmpId,_tmpTitulo,_tmpTieneEstablecimiento,_tmpTipoEntrada,_tmpInputOriginal,_tmpFotoPath,_tmpLatitud,_tmpLongitud,_tmpNombreUbicacion,_tmpFecha)
          val _tmpGastosCollection: MutableList<GastoEntity>
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfId)
          _tmpGastosCollection = checkNotNull(_collectionGastos.get(_tmpKey_1))
          _item = RegistroConGastos(_tmpRegistro,_tmpGastosCollection)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalGastado(): Flow<Double> {
    val _sql: String = "SELECT COALESCE(SUM(monto), 0.0) FROM gastos"
    return createFlow(__db, false, arrayOf("gastos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Double
        if (_stmt.step()) {
          val _tmp: Double
          _tmp = _stmt.getDouble(0)
          _result = _tmp
        } else {
          _result = 0.0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalGastadoEnRango(start: Long, end: Long): Flow<Double> {
    val _sql: String = """
        |
        |        SELECT COALESCE(SUM(g.monto), 0.0) FROM gastos g 
        |        INNER JOIN registros r ON g.registroId = r.id 
        |        WHERE r.fecha BETWEEN ? AND ?
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        val _result: Double
        if (_stmt.step()) {
          val _tmp: Double
          _tmp = _stmt.getDouble(0)
          _result = _tmp
        } else {
          _result = 0.0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGastosPorCategoriaEnRango(start: Long, end: Long):
      Flow<List<CategoriaTotal>> {
    val _sql: String = """
        |
        |        SELECT g.categoria, SUM(g.monto) as total 
        |        FROM gastos g 
        |        INNER JOIN registros r ON g.registroId = r.id 
        |        WHERE r.fecha BETWEEN ? AND ? 
        |        GROUP BY g.categoria 
        |        ORDER BY total DESC
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        val _columnIndexOfCategoria: Int = 0
        val _columnIndexOfTotal: Int = 1
        val _result: MutableList<CategoriaTotal> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoriaTotal
          val _tmpCategoria: String
          _tmpCategoria = _stmt.getText(_columnIndexOfCategoria)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          _item = CategoriaTotal(_tmpCategoria,_tmpTotal)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopProductosEnRango(start: Long, end: Long): Flow<List<ProductoTotal>> {
    val _sql: String = """
        |
        |        SELECT g.producto, SUM(g.monto) as total, COUNT(*) as cantidad
        |        FROM gastos g 
        |        INNER JOIN registros r ON g.registroId = r.id 
        |        WHERE r.fecha BETWEEN ? AND ? 
        |        GROUP BY g.producto 
        |        ORDER BY total DESC 
        |        LIMIT 5
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        val _columnIndexOfProducto: Int = 0
        val _columnIndexOfTotal: Int = 1
        val _columnIndexOfCantidad: Int = 2
        val _result: MutableList<ProductoTotal> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProductoTotal
          val _tmpProducto: String
          _tmpProducto = _stmt.getText(_columnIndexOfProducto)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          val _tmpCantidad: Int
          _tmpCantidad = _stmt.getLong(_columnIndexOfCantidad).toInt()
          _item = ProductoTotal(_tmpProducto,_tmpTotal,_tmpCantidad)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGastoDiarioEnRango(start: Long, end: Long): Flow<List<DiaTotal>> {
    val _sql: String = """
        |
        |        SELECT DATE(r.fecha / 1000, 'unixepoch', 'localtime') as dia, 
        |               SUM(g.monto) as total
        |        FROM gastos g 
        |        INNER JOIN registros r ON g.registroId = r.id 
        |        WHERE r.fecha BETWEEN ? AND ? 
        |        GROUP BY dia 
        |        ORDER BY dia ASC
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        val _columnIndexOfDia: Int = 0
        val _columnIndexOfTotal: Int = 1
        val _result: MutableList<DiaTotal> = mutableListOf()
        while (_stmt.step()) {
          val _item: DiaTotal
          val _tmpDia: String
          _tmpDia = _stmt.getText(_columnIndexOfDia)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          _item = DiaTotal(_tmpDia,_tmpTotal)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCategoriasDeRegistro(registroId: Int): Flow<List<String>> {
    val _sql: String = """
        |
        |        SELECT DISTINCT g.categoria FROM gastos g
        |        INNER JOIN registros r ON g.registroId = r.id
        |        WHERE r.id = ?
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("gastos", "registros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, registroId.toLong())
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteRegistroById(id: Int) {
    val _sql: String = "DELETE FROM registros WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteGastosByRegistroId(registroId: Int) {
    val _sql: String = "DELETE FROM gastos WHERE registroId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, registroId.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  private
      fun __fetchRelationshipgastosAscomIanlwAsiestamosDataLocalEntityGastoEntity(_connection: SQLiteConnection,
      _map: LongSparseArray<MutableList<GastoEntity>>) {
    if (_map.isEmpty()) {
      return
    }
    if (_map.size() > 999) {
      recursiveFetchLongSparseArray(_map, true) { _tmpMap ->
        __fetchRelationshipgastosAscomIanlwAsiestamosDataLocalEntityGastoEntity(_connection,
            _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `id`,`registroId`,`monto`,`producto`,`descripcionIA`,`categoria` FROM `gastos` WHERE `registroId` IN (")
    val _inputSize: Int = _map.size()
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (i in 0 until _map.size()) {
      val _item: Long = _map.keyAt(i)
      _stmt.bindLong(_argIndex, _item)
      _argIndex++
    }
    try {
      val _itemKeyIndex: Int = getColumnIndex(_stmt, "registroId")
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfId: Int = 0
      val _columnIndexOfRegistroId: Int = 1
      val _columnIndexOfMonto: Int = 2
      val _columnIndexOfProducto: Int = 3
      val _columnIndexOfDescripcionIA: Int = 4
      val _columnIndexOfCategoria: Int = 5
      while (_stmt.step()) {
        val _tmpKey: Long
        _tmpKey = _stmt.getLong(_itemKeyIndex)
        val _tmpRelation: MutableList<GastoEntity>? = _map.get(_tmpKey)
        if (_tmpRelation != null) {
          val _item_1: GastoEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpRegistroId: Int
          _tmpRegistroId = _stmt.getLong(_columnIndexOfRegistroId).toInt()
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpProducto: String
          _tmpProducto = _stmt.getText(_columnIndexOfProducto)
          val _tmpDescripcionIA: String
          _tmpDescripcionIA = _stmt.getText(_columnIndexOfDescripcionIA)
          val _tmpCategoria: String
          _tmpCategoria = _stmt.getText(_columnIndexOfCategoria)
          _item_1 =
              GastoEntity(_tmpId,_tmpRegistroId,_tmpMonto,_tmpProducto,_tmpDescripcionIA,_tmpCategoria)
          _tmpRelation.add(_item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
