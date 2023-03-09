package com.example.uiexample.domain.usecases

import com.example.uiexample.domain.models.HealthDomain
import com.example.uiexample.domain.repository.HealthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetHealthListUseCase {

    suspend fun invoke(): Flow<List<HealthDomain>>
}

class GetHealthListUseCaseImpl @Inject constructor(
    private val repository: HealthRepository) :
    GetHealthListUseCase {

    override suspend fun invoke(): Flow<List<HealthDomain>> =
        repository.fetchHealthDomainList()


}