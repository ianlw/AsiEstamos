package com.ianlw.asiestamos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ianlw.asiestamos.data.local.dao.RegistroDao
import com.ianlw.asiestamos.data.local.entity.GastoEntity
import com.ianlw.asiestamos.data.local.entity.RegistroEntity

@Database(
    entities = [RegistroEntity::class, GastoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun registroDao(): RegistroDao
}
