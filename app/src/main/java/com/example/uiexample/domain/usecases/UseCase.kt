package com.example.uiexample.domain.usecases

import kotlinx.coroutines.flow.Flow

interface UseCase<T> {

    suspend fun invoke(): Flow<T>
}