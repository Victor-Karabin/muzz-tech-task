package com.muzz.ui.chat.mock

import com.muzz.data.chat.MessageRepo
import com.muzz.domain.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessageRepoMockFactory {

    companion object {
        fun create(): MessageRepo {
            return object : MessageRepo {
                override fun subscribe(): Flow<List<Message>> {
                    return flow {}
                }

                override suspend fun store(message: Message): Result<Message> {
                    return Result.success(message)
                }
            }
        }
    }
}