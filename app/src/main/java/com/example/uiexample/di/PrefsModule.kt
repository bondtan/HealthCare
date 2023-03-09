package com.example.uiexample.di

import com.example.uiexample.data.prefsstore.PrefsStore
import com.example.uiexample.data.prefsstore.PrefsStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefsModule {

    @Singleton
    @Binds
    abstract fun bindPrefsStore(
        prefsStore: PrefsStoreImpl
    ) : PrefsStore


}