// EventDao.kt
package com.example.dicodingevent.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(eventEntity: EventEntity)
    @Query("SELECT * FROM EventEntity WHERE id = :eventId")
    suspend fun getEventById(eventId: kotlin.Int): EventEntity?

    @Query("SELECT * FROM EventEntity")
    suspend fun getAllFavoriteEvents(): List<EventEntity>

    @Query("DELETE FROM EventEntity WHERE id = :eventId")
    suspend fun deleteById(eventId: kotlin.Int)
}