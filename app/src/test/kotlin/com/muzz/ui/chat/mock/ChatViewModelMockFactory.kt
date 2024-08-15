package com.muzz.ui.chat.mock

import com.muzz.common.TickerImpl
import com.muzz.data.chat.MessageRepo
import com.muzz.ui.chat.ChatViewModel

class ChatViewModelMockFactory {

    companion object {

        private val messageRepo = MessageRepoMockFactory.create()

        fun create(user1: String, user2: String): ChatViewModel {
            return ChatViewModel(user1, user2, messageRepo, TickerImpl())
        }

        fun create(): ChatViewModel {
            return ChatViewModel("Sarach", "Andy", messageRepo, TickerImpl())
        }

        fun create(messageRepo: MessageRepo): ChatViewModel {
            return ChatViewModel("Sarach", "Andy", messageRepo, TickerImpl())
        }
    }
}