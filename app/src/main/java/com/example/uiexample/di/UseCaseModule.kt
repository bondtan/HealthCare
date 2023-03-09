package com.example.uiexample.di

import com.example.uiexample.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindDeleteHealthItemUseCase(
        useCase: DeleteHealthItemUseCaseImpl
    ): DeleteHealthItemUseCase

    @Binds
    abstract fun bindGetHealthItemUseCase(
        useCase: GetHealthItemUseCaseImpl
    ): GetHealthItemUseCase

    @Binds
    abstract fun bindsGetHealthListUseCase(
        useCase: GetHealthListUseCaseImpl
    ): GetHealthListUseCase

    @Binds
    abstract fun bindsInsertHealthItemUseCase(
        useCase: InsetHealthItemUseCaseImpl
    ): InsetHealthItemUseCase
}