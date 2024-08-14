package com.muzz.ui.chat

class ChatViewModelMockFactory {

    companion object {
        fun create(user1: String, user2: String): ChatViewModel {
            return ChatViewModel(user1, user2)
        }
    }
}