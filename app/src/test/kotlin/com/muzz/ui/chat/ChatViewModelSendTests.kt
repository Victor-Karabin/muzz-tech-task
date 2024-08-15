package com.muzz.ui.chat

import com.muzz.common.MainCoroutineRule
import com.muzz.data.chat.MessageRepo
import com.muzz.domain.Message
import com.muzz.ui.chat.mock.ChatViewModelMockFactory
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelSendTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    @Test
    fun `given user input when success send then user input is cleared`() = runTest {
        val text = "Hello"
        val message = Message(
            id = "id",
            authorId = "author",
            message = text,
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        )
        val repo = mock<MessageRepo> {
            on { subscribe() }.doReturn(flow {})
            onBlocking { store(any()) }.doReturn(Result.success(message))
        }
        val viewModel = ChatViewModelMockFactory.create(repo)
        viewModel.userInput(text)
        viewModel.clickSend()
        advanceUntilIdle()

        assertEquals("", viewModel.userInput.value)
    }
}