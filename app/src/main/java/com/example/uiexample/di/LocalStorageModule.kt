package com.example.uiexample.di

import android.content.Context
import androidx.room.Room
import com.example.uiexample.data.localdatasourse.HealthDao
import com.example.uiexample.data.localdatasourse.HealthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    @Provides
    @Singleton
    fun provideHealthDatabase (@ApplicationContext context: Context): HealthDatabase =
        Room.databaseBuilder (
            context,
            HealthDatabase::class.java,
            "health"
        ).build()

    @Provides
    @Singleton
    fun provideHealthDao (database: HealthDatabase): HealthDao =
        database.getHealthDao()
}