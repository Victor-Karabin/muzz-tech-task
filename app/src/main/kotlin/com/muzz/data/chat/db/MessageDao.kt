package com.muzz.data.chat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MessageDao {

    @Query("SELECT * FROM message")
    fun subscribe(): Flow<List<MessageDto>>

    @Insert
    suspend fun save(message: MessageDto): Long
}