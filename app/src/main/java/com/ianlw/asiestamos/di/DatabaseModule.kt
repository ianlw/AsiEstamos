package com.ianlw.asiestamos.di

import android.content.Context
import androidx.room.Room
import com.ianlw.asiestamos.data.local.AppDatabase
import com.ianlw.asiestamos.data.local.dao.RegistroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "asi_estamos_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRegistroDao(database: AppDatabase): RegistroDao {
        return database.registroDao()
    }
}
