package com.muzz.ui.chat

import com.muzz.common.MainCoroutineRule
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ChatViewModelSendTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val viewModel by lazy { ChatViewModelMockFactory.create() }

    @Test
    fun `given user input when success send then user input is cleared`() {
        viewModel.userInput("Hello")
        viewModel.clickSend()

        assertEquals("", viewModel.userInput.value)
    }
}