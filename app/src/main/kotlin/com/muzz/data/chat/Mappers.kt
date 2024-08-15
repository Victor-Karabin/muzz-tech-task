package com.muzz.data.chat

import com.muzz.data.chat.db.MessageDto
import com.muzz.domain.Message
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

internal fun Message.toDto(timeZone: TimeZone): MessageDto {
    return MessageDto(
        uid = this.id.toLongOrNull() ?: 0L,
        author = this.authorId,
        text = this.message,
        createdAt = this.dateTime.toInstant(timeZone).toEpochMilliseconds()
    )
}

internal fun MessageDto.toModel(timeZone: TimeZone): Message {
    return Message(
        id = this.uid.toString(),
        authorId = this.author,
        message = this.text,
        dateTime = Instant.fromEpochMilliseconds(this.createdAt).toLocalDateTime(timeZone)
    )
}