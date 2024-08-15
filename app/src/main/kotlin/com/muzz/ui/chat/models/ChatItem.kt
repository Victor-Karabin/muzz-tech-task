package com.muzz.ui.chat.models

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDateTime

@Stable
internal sealed class ChatItem(open val id: String) {

    internal data class Message(
        override val id: String,
        val isActiveUser: Boolean,
        val message: String
    ) : ChatItem(id)

    internal data class DateTime(
        override val id: String,
        @Stable
        val dateTime: LocalDateTime
    ) : ChatItem(id)
}