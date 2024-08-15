package com.muzz.data.chat

import com.muzz.common.Ticker
import com.muzz.data.chat.db.MessageDao
import com.muzz.di.coroutines.IODispatcher
import com.muzz.domain.Message
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class MessageRepoImpl @Inject constructor(
    private val messageDao: MessageDao,
    @IODispatcher
    private val io: CoroutineDispatcher,
    private val ticker: Ticker
) : MessageRepo {

    override fun subscribe(): Flow<List<Message>> {
        return messageDao.subscribe()
            .map { messages -> messages.map { dto -> dto.toModel(ticker.currentTimezone()) } }
    }

    override suspend fun store(message: Message): Result<Message> {
        return withContext(io) {
            try {
                val id = messageDao.save(message.toDto(ticker.currentTimezone()))
                Result.success(message.copy(id = id.toString()))
            } catch (ex: Exception) {
                Result.failure(ex)
            }
        }
    }
}