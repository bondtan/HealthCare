package com.example.uiexample.domain.usecases

import com.example.uiexample.domain.models.HealthDomain
import com.example.uiexample.domain.repository.HealthRepository
import javax.inject.Inject

interface DeleteHealthItemUseCase {

    suspend fun invoke(healthIdDomain: HealthDomain)
}


class DeleteHealthItemUseCaseImpl @Inject constructor(
    private val repository: HealthRepository
): DeleteHealthItemUseCase {
    override suspend fun invoke(healthIdDomain: HealthDomain) =
        repository.deleteHealthDomainFromLocalSource(healthIdDomain)
}
