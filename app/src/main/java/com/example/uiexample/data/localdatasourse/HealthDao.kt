package com.example.uiexample.data.localdatasourse

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setHealth (healthEntity: HealthEntity)

    @Delete
    suspend fun deleteHealth (healthEntity: HealthEntity)

    @Query("SELECT * FROM health ORDER BY total_time DESC")
    fun getHealthEntityList (): Flow<List<HealthEntity>>

    @Query("SELECT * FROM health WHERE id = :id ")
    fun getHealth (id: Int): Flow<HealthEntity>

}