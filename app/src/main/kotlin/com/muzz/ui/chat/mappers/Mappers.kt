package com.muzz.ui.chat.mappers

import com.muzz.domain.Message
import com.muzz.ui.chat.models.ChatItem
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.Locale

internal fun LocalDateTime.toDayOfWeekText(): String {
    val ms = this.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dateFormat.format(ms)
}

internal fun LocalDateTime.toTimeText(): String {
    val hour = if (this.hour <= 9) "0${this.hour}" else this.hour.toString()
    val min = if (this.minute <= 9) "0${this.minute}" else this.minute.toString()

    return "$hour:$min"
}

internal fun Message.toItem(activeUser: String): ChatItem.Message {
    return ChatItem.Message(
        id = this.id,
        isActiveUser = this.authorId == activeUser,
        message = this.message
    )
}