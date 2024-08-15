package com.muzz.data.chat

import com.muzz.domain.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepo {

    fun subscribe(): Flow<List<Message>>

    suspend fun store(message: Message): Result<Message>
}