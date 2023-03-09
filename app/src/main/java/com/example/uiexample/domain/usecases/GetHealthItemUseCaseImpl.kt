package com.example.uiexample.domain.usecases

import com.example.uiexample.domain.models.HealthDomain
import com.example.uiexample.domain.repository.HealthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetHealthItemUseCase {

    suspend fun invoke(healthId: Int): Flow<HealthDomain>
}

class GetHealthItemUseCaseImpl @Inject constructor(
    private val repository: HealthRepository
) : GetHealthItemUseCase {

    override suspend fun invoke(healthId: Int): Flow<HealthDomain> =
        repository.fetchHealthDomain(healthId)
}