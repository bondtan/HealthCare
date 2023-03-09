package com.example.uiexample.di

import com.example.uiexample.notification.ReminderManager
import com.example.uiexample.notification.ReminderManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {

    @Singleton
    @Binds
    abstract fun bindRemindsManager(
        manager: ReminderManagerImpl
    ): ReminderManager
}