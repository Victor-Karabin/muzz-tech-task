package com.muzz.ui.chat.mappers

import com.muzz.domain.Message
import com.muzz.ui.chat.models.ChatItem
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID
import kotlin.time.Duration

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

internal fun List<Message>.toItems(
    timeDelay: Duration,
    fastMessages: Duration,
    activeUser: String,
    timeZone: TimeZone
): List<ChatItem> {

    val items = ArrayList<ChatItem>()

    this.forEachIndexed { index, msg ->
        val instant = msg.dateTime.toInstant(timeZone)
        val prevMsg = if (index > 0) this[index - 1] else null
        val prevInstant = prevMsg?.dateTime?.toInstant(timeZone)
        val duration = prevInstant?.let { prev -> instant.minus(prev) } ?: Duration.ZERO

        when {
            // add date time item if there is no prev messages or time-gape is more timeItemDelay
            prevInstant == null || duration >= timeDelay -> {
                val dateTimeItem = ChatItem.DateTime(
                    id = msg.dateTime.toString(),
                    dateTime = msg.dateTime
                )
                items.add(dateTimeItem)
            }

            // add date time item if there is no prev messages or time-gape is more timeItemDelay
            index <= this.lastIndex -> {
                val divider = if (prevMsg.authorId == msg.authorId && duration <= fastMessages) {
                    ChatItem.SmallDivider(UUID.randomUUID().toString())
                } else {
                    ChatItem.LargeDivider(UUID.randomUUID().toString())
                }

                items.add(divider)
            }
        }

        val messageItem = msg.toItem(activeUser)
        items.add(messageItem)
    }

    return items
}