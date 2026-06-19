package com.ianlw.asiestamos.di

import com.ianlw.asiestamos.data.repository.GastoRepositoryImpl
import com.ianlw.asiestamos.data.repository.GeminiRepositoryImpl
import com.ianlw.asiestamos.domain.repository.GastoRepository
import com.ianlw.asiestamos.domain.repository.GeminiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGastoRepository(impl: GastoRepositoryImpl): GastoRepository

    @Binds
    @Singleton
    abstract fun bindGeminiRepository(impl: GeminiRepositoryImpl): GeminiRepository
}
