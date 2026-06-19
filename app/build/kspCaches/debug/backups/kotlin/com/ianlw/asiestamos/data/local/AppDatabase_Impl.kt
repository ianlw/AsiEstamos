package com.ianlw.asiestamos.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.ianlw.asiestamos.`data`.local.dao.RegistroDao
import com.ianlw.asiestamos.`data`.local.dao.RegistroDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _registroDao: Lazy<RegistroDao> = lazy {
    RegistroDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "c690a83c7dc93acc2c457803e4fca006", "5b1fa7f2ac0529c8efee0faa9f2acd29") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `registros` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titulo` TEXT NOT NULL, `tieneEstablecimiento` INTEGER NOT NULL, `tipoEntrada` TEXT NOT NULL, `inputOriginal` TEXT NOT NULL, `fotoPath` TEXT, `latitud` REAL, `longitud` REAL, `nombreUbicacion` TEXT, `fecha` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `gastos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `registroId` INTEGER NOT NULL, `monto` REAL NOT NULL, `producto` TEXT NOT NULL, `descripcionIA` TEXT NOT NULL, `categoria` TEXT NOT NULL, FOREIGN KEY(`registroId`) REFERENCES `registros`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_gastos_registroId` ON `gastos` (`registroId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c690a83c7dc93acc2c457803e4fca006')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `registros`")
        connection.execSQL("DROP TABLE IF EXISTS `gastos`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsRegistros: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRegistros.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("titulo", TableInfo.Column("titulo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("tieneEstablecimiento", TableInfo.Column("tieneEstablecimiento",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("tipoEntrada", TableInfo.Column("tipoEntrada", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("inputOriginal", TableInfo.Column("inputOriginal", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("fotoPath", TableInfo.Column("fotoPath", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("latitud", TableInfo.Column("latitud", "REAL", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("longitud", TableInfo.Column("longitud", "REAL", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("nombreUbicacion", TableInfo.Column("nombreUbicacion", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRegistros.put("fecha", TableInfo.Column("fecha", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRegistros: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRegistros: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRegistros: TableInfo = TableInfo("registros", _columnsRegistros,
            _foreignKeysRegistros, _indicesRegistros)
        val _existingRegistros: TableInfo = read(connection, "registros")
        if (!_infoRegistros.equals(_existingRegistros)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |registros(com.ianlw.asiestamos.data.local.entity.RegistroEntity).
              | Expected:
              |""".trimMargin() + _infoRegistros + """
              |
              | Found:
              |""".trimMargin() + _existingRegistros)
        }
        val _columnsGastos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGastos.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGastos.put("registroId", TableInfo.Column("registroId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGastos.put("monto", TableInfo.Column("monto", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGastos.put("producto", TableInfo.Column("producto", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGastos.put("descripcionIA", TableInfo.Column("descripcionIA", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGastos.put("categoria", TableInfo.Column("categoria", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGastos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysGastos.add(TableInfo.ForeignKey("registros", "CASCADE", "NO ACTION",
            listOf("registroId"), listOf("id")))
        val _indicesGastos: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesGastos.add(TableInfo.Index("index_gastos_registroId", false, listOf("registroId"),
            listOf("ASC")))
        val _infoGastos: TableInfo = TableInfo("gastos", _columnsGastos, _foreignKeysGastos,
            _indicesGastos)
        val _existingGastos: TableInfo = read(connection, "gastos")
        if (!_infoGastos.equals(_existingGastos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |gastos(com.ianlw.asiestamos.data.local.entity.GastoEntity).
              | Expected:
              |""".trimMargin() + _infoGastos + """
              |
              | Found:
              |""".trimMargin() + _existingGastos)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "registros", "gastos")
  }

  public override fun clearAllTables() {
    super.performClear(true, "registros", "gastos")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(RegistroDao::class, RegistroDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun registroDao(): RegistroDao = _registroDao.value
}
