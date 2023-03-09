package com.example.uiexample.domain.usecases

import com.example.uiexample.domain.models.HealthDomain
import com.example.uiexample.domain.repository.HealthRepository
import javax.inject.Inject

interface InsetHealthItemUseCase {

    suspend fun invoke(healthIdDomain: HealthDomain)
}

class InsetHealthItemUseCaseImpl @Inject constructor(
    private val repository: HealthRepository
): InsetHealthItemUseCase {
    override suspend fun invoke(healthIdDomain: HealthDomain) =
        repository.setHeathDomainToLocalSource(healthIdDomain)
}