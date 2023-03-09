package com.example.uiexample.data.localdatasourse

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.uiexample.data.localdatasourse.converters.DateConverters
import com.example.uiexample.data.localdatasourse.converters.TimeConverters

@Database(
    entities = [HealthEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters( TimeConverters::class, DateConverters::class)
abstract class HealthDatabase: RoomDatabase() {

    abstract fun getHealthDao(): HealthDao
}