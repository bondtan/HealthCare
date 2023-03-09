package com.example.uiexample.data.repository

import com.example.uiexample.data.localdatasourse.HealthDao
import com.example.uiexample.data.localdatasourse.fromDataToDomain
import com.example.uiexample.domain.models.HealthDomain
import com.example.uiexample.domain.models.fromDomainToData
import com.example.uiexample.domain.repository.HealthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HealthRepositoryImpl @Inject constructor(
    private val dao: HealthDao
): HealthRepository {

    override suspend fun setHeathDomainToLocalSource (healthDomain: HealthDomain) {
        dao.setHealth(healthDomain.fromDomainToData())
    }

    override suspend fun deleteHealthDomainFromLocalSource (healthDomain: HealthDomain) {
        dao.deleteHealth(healthDomain.fromDomainToData())
    }

    override fun fetchHealthDomainList(): Flow<List<HealthDomain>>  =
        dao.getHealthEntityList().map { list ->
            list.map { healthEntity ->
                healthEntity.fromDataToDomain()
            }
        }

    override fun fetchHealthDomain( healthId: Int) : Flow <HealthDomain> =
        dao.getHealth(healthId).map { it.fromDataToDomain() }

}