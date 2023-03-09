package com.example.uiexample.domain.repository

import com.example.uiexample.domain.models.HealthDomain
import kotlinx.coroutines.flow.Flow

interface HealthRepository {

    suspend fun setHeathDomainToLocalSource(healthDomain: HealthDomain)

    suspend fun deleteHealthDomainFromLocalSource(healthDomain: HealthDomain)

    fun fetchHealthDomainList(): Flow<List<HealthDomain>>

    fun fetchHealthDomain( healthId: Int) : Flow <HealthDomain>
}