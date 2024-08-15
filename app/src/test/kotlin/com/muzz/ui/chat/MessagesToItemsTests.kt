package com.muzz.ui.chat

import com.muzz.domain.Message
import com.muzz.ui.chat.mappers.toItems
import com.muzz.ui.chat.models.ChatItem
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.junit.Test
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MessagesToItemsTests {

    @Test
    fun `given single message when map then date time item is added`() {
        val dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        val message = Message(
            id = "id",
            authorId = "Sarah",
            dateTime = dateTime,
            message = "Hello"
        )
        val delay = 1.toDuration(DurationUnit.HOURS)
        val timeZone = TimeZone.currentSystemDefault()

        val items = listOf(message).toItems(delay, "user", timeZone)

        assertEquals(2, items.size)
        assertTrue(items.first() is ChatItem.DateTime)
    }

    @Test
    fun `given single message when map then date time item with message-date is added`() {
        val dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        val message = Message(
            id = "id",
            authorId = "Sarah",
            dateTime = dateTime,
            message = "Hello"
        )
        val delay = 1.toDuration(DurationUnit.HOURS)
        val timeZone = TimeZone.currentSystemDefault()

        val items = listOf(message).toItems(delay, "user", timeZone)

        assertEquals(dateTime, (items.first() as ChatItem.DateTime).dateTime)
    }

    @Test
    fun `given 2 fast messages when map then they are placed one by one`() {
        val id1 = "id1"
        val message1 = Message(
            id = id1,
            authorId = "Sarah",
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val id2 = "id2"
        val message2 = Message(
            id = id2,
            authorId = "Sarah",
            dateTime = LocalDateTime.parse("2024-08-15T14:10:01"),
            message = "How are you?"
        )

        val delay = 1.toDuration(DurationUnit.HOURS)
        val timeZone = TimeZone.currentSystemDefault()

        val items = listOf(message1, message2).toItems(delay, "user", timeZone)

        assertEquals(id1, (items[1] as ChatItem.Message).id)
        assertEquals(id2, (items[2] as ChatItem.Message).id)
    }

    @Test
    fun `given 2 delayed messages when map then date time item is added between them`() {
        val id1 = "id1"
        val message1 = Message(
            id = id1,
            authorId = "Sarah",
            dateTime = LocalDateTime.parse("2024-08-15T12:09:01"),
            message = "Hello"
        )

        val id2 = "id2"
        val message2 = Message(
            id = id2,
            authorId = "Sarah",
            dateTime = LocalDateTime.parse("2024-08-15T14:10:01"),
            message = "How are you?"
        )

        val delay = 1.toDuration(DurationUnit.HOURS)
        val timeZone = TimeZone.currentSystemDefault()

        val items = listOf(message1, message2).toItems(delay, "user", timeZone)

        assertEquals(id1, (items[1] as ChatItem.Message).id)
        assertTrue(items[2] is ChatItem.DateTime)
        assertEquals(id2, (items[3] as ChatItem.Message).id)
    }
}