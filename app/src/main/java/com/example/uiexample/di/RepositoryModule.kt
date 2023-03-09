package com.example.uiexample.di

import com.example.uiexample.data.repository.HealthRepositoryImpl
import com.example.uiexample.domain.repository.HealthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        repository: HealthRepositoryImpl
    ): HealthRepository
}