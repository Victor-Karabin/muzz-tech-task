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

    private val fastDuration = 20.toDuration(DurationUnit.SECONDS)
    private val dateTimeDelay = 1.toDuration(DurationUnit.HOURS)
    private val timeZone = TimeZone.currentSystemDefault()

    @Test
    fun `given single message when map then date time and message items are added`() {
        val authorId = "Sarah"
        val message = Message(
            id = "id",
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val items = listOf(message).toItems(dateTimeDelay, fastDuration, authorId, timeZone)

        assertEquals(2, items.size)
    }

    @Test
    fun `given single message when map then date time item is first`() {
        val authorId = "Sarah"
        val message = Message(
            id = "id",
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val items = listOf(message).toItems(dateTimeDelay, fastDuration, authorId, timeZone)

        assertTrue(items.first() is ChatItem.DateTime)
    }

    @Test
    fun `given single message when map then message item is second`() {
        val authorId = "Sarah"
        val message = Message(
            id = "id",
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val items = listOf(message).toItems(dateTimeDelay, fastDuration, authorId, timeZone)

        assertTrue(items[1] is ChatItem.Message)
    }

    @Test
    fun `given single message when map then message item with correct id is second`() {
        val messageId = "id"
        val authorId = "Sarah"
        val message = Message(
            id = messageId,
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val items = listOf(message).toItems(dateTimeDelay, fastDuration, authorId, timeZone)

        assertEquals(messageId, (items[1] as ChatItem.Message).id)
    }

    @Test
    fun `given single message when map then date time item with message-date is added`() {
        val dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        val authorId = "Sarah"
        val message = Message(
            id = "id",
            authorId = authorId,
            dateTime = dateTime,
            message = "Hello"
        )
        val timeZone = TimeZone.currentSystemDefault()

        val items = listOf(message).toItems(dateTimeDelay, fastDuration, authorId, timeZone)

        assertEquals(dateTime, (items.first() as ChatItem.DateTime).dateTime)
    }

    @Test
    fun `given 2 fast messages by the same author when map then small divider between them`() {
        val authorId = "Sarah"
        val id1 = "id1"
        val message1 = Message(
            id = id1,
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val id2 = "id2"
        val message2 = Message(
            id = id2,
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:02"),
            message = "How are you?"
        )

        val items = listOf(message1, message2)
            .toItems(dateTimeDelay, fastDuration, authorId, timeZone)

        assertEquals(id1, (items[1] as ChatItem.Message).id)
        assertTrue(items[2] is ChatItem.SmallDivider)
        assertEquals(id2, (items[3] as ChatItem.Message).id)
    }

    @Test
    fun `given 2 slow messages by the same author when map then large divider between them`() {
        val authorId = "Sarah"
        val id1 = "id1"
        val message1 = Message(
            id = id1,
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val id2 = "id2"
        val message2 = Message(
            id = id2,
            authorId = authorId,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:22"),
            message = "How are you?"
        )

        val items = listOf(message1, message2)
            .toItems(dateTimeDelay, fastDuration, authorId, timeZone)

        assertEquals(id1, (items[1] as ChatItem.Message).id)
        assertTrue(items[2] is ChatItem.LargeDivider)
        assertEquals(id2, (items[3] as ChatItem.Message).id)
    }

    @Test
    fun `given 2 fast messages by different authors when map then large divider between them`() {
        val id1 = "id1"
        val activeUser = "Sarah"
        val message1 = Message(
            id = id1,
            authorId = "Sarah",
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01"),
            message = "Hello"
        )

        val id2 = "id2"
        val message2 = Message(
            id = id2,
            authorId = "Andy",
            dateTime = LocalDateTime.parse("2024-08-15T14:09:02"),
            message = "How are you?"
        )

        val items = listOf(message1, message2)
            .toItems(dateTimeDelay, fastDuration, activeUser, timeZone)

        assertEquals(id1, (items[1] as ChatItem.Message).id)
        assertTrue(items[2] is ChatItem.LargeDivider)
        assertEquals(id2, (items[3] as ChatItem.Message).id)
    }

    @Test
    fun `given 2 slow messages by different authors when map then large divider between them`() {
        val activeUser = "Sarah"
        val id1 = "id1"
        val message1 = Message(
            id = id1,
            authorId = activeUser,
            dateTime = LocalDateTime.parse("2024-08-15T12:09:01"),
            message = "Hello"
        )

        val id2 = "id2"
        val message2 = Message(
            id = id2,
            authorId = "Andy",
            dateTime = LocalDateTime.parse("2024-08-15T12:09:22"),
            message = "How are you?"
        )

        val timeZone = TimeZone.currentSystemDefault()

        val items = listOf(message1, message2)
            .toItems(dateTimeDelay, fastDuration, activeUser, timeZone)

        assertEquals(id1, (items[1] as ChatItem.Message).id)
        assertTrue(items[2] is ChatItem.LargeDivider)
        assertEquals(id2, (items[3] as ChatItem.Message).id)
    }

    @Test
    fun `given 2 delayed messages by the same author when map then date time between them`() {
        val activeUser = "Sarah"
        val id1 = "id1"
        val message1 = Message(
            id = id1,
            authorId = activeUser,
            dateTime = LocalDateTime.parse("2024-08-15T12:09:01"),
            message = "Hello"
        )

        val id2 = "id2"
        val message2 = Message(
            id = id2,
            authorId = activeUser,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:22"),
            message = "How are you?"
        )

        val timeZone = TimeZone.currentSystemDefault()

        val items = listOf(message1, message2)
            .toItems(dateTimeDelay, fastDuration, activeUser, timeZone)

        assertEquals(id1, (items[1] as ChatItem.Message).id)
        assertTrue(items[2] is ChatItem.DateTime)
        assertEquals(id2, (items[3] as ChatItem.Message).id)
    }
}