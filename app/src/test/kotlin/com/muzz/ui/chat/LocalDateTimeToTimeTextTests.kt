package com.muzz.ui.chat

import com.muzz.ui.chat.mappers.toTimeText
import junit.framework.TestCase.assertEquals
import kotlinx.datetime.LocalDateTime
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LocalDateTimeToTimeTextTests(
    private val dateTime: LocalDateTime,
    private val timeText: String
) {

    @Test
    fun `given local date time when map then expected result`() {
        val result = dateTime.toTimeText()
        assertEquals(timeText, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "#{index}: {0} is correct: {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(LocalDateTime.parse("2024-08-15T14:09:01"), "14:09"),
                arrayOf(LocalDateTime.parse("2024-08-15T00:00:01"), "00:00"),
                arrayOf(LocalDateTime.parse("2024-08-15T23:30:01"), "23:30"),
                arrayOf(LocalDateTime.parse("2024-08-15T04:19:01"), "04:19")
            )
        }
    }
}