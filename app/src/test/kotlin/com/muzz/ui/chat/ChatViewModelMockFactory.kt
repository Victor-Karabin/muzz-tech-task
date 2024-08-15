package com.muzz.ui.chat

import com.muzz.common.Ticker
import com.muzz.data.chat.MessageRepo
import org.mockito.kotlin.mock

class ChatViewModelMockFactory {

    companion object {
        fun create(user1: String, user2: String): ChatViewModel {
            return ChatViewModel(user1, user2, mock<MessageRepo>(), mock<Ticker>())
        }

        fun create(): ChatViewModel {
            return ChatViewModel("Sarach", "Andy", mock<MessageRepo>(), mock<Ticker>())
        }
    }
}